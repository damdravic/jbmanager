package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.User;
import com.anaximel.bmanager.appsecurity.enumeration.Role;
import com.anaximel.bmanager.appsecurity.exception.domain.EmailExistException;
import com.anaximel.bmanager.appsecurity.exception.domain.EmailNotFoundException;
import com.anaximel.bmanager.appsecurity.exception.domain.UsernameExistException;
import com.anaximel.bmanager.appsecurity.repository.UserRepository;
import com.anaximel.bmanager.appsecurity.security.UserPrincipal;
import com.anaximel.bmanager.appsecurity.service.EmailService;
import com.anaximel.bmanager.appsecurity.service.LoginAttemptService;
import com.anaximel.bmanager.appsecurity.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolFamily;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.anaximel.bmanager.appsecurity.constant.FileConstant.*;
import static com.anaximel.bmanager.appsecurity.constant.UserImplConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;
    private BCryptPasswordEncoder  passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           LoginAttemptService loginAttemptService,
                           EmailService emailService) {
        this.loginAttemptService = loginAttemptService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user  = userRepository.findUserByUsername(username);
       if(user == null){
           LOGGER.error(NO_USERNAME_FOUND_BY_NAME + username);

           throw new UsernameNotFoundException(NO_USERNAME_FOUND_BY_NAME + username);
       }else{
          validateLoginAttempt(user);

           user.setLastLoginDateDisplay(user.getLastLoginDate());
           user.setLastLoginDate(new Date());
           userRepository.save(user);

           UserPrincipal userPrincipal = new UserPrincipal(user);
           LOGGER.info("Returning found user by username: " + username);
           return userPrincipal;

       }
    }

    private void validateLoginAttempt(User user)  {
        if(user.isNotLocked()){

            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())){
                user.setNotLocked(false);
            }else{
                user.setNotLocked(true);
            }

        }else{
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }


    @Override
    public User register(String firstName, String lastName, String username, String email) throws UsernameExistException, EmailExistException, MessagingException {

        validateNewUsernameAndEmail(StringUtils.EMPTY,username,email);
        User user =  new User();
        user.setUserId(generateId());
        String password = generatePassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodePassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileUrl(username));

        userRepository.save(user);
        emailService.sendNewPasswordEmail(firstName,password,email);
        LOGGER.info("User password " + password);

        return user;
    }

    private String getTemporaryProfileUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateId() {
        return RandomStringUtils.randomNumeric(10);


    }

    private User validateNewUsernameAndEmail(String currentUsername,String newUsername,String newEmail )
                                           throws UsernameExistException, EmailExistException {

        User userByNewEmail = findUserByEmail(newEmail);
        User userByNewUsername = findUserByUsername(newUsername);

        if(StringUtils.isNotBlank(currentUsername)){
            User currentUser = findUserByUsername(currentUsername);
            if(currentUser == null){
                throw new UsernameNotFoundException(NO_USERNAME_FOUND_BY_NAME+ " "+  currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())){
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);

            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())){
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);

            }
            return currentUser;
        } else{
            if(userByNewUsername != null){
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null){
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;


        }





    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User addNewUser( String firstName, String lastName, String username, String email,
                            String role, boolean isNotLocked, boolean isActive, MultipartFile profileImage)
                                        throws UsernameExistException, EmailExistException, IOException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username,email );
        User user =  new User();
        user.setUserId(generateId());
        String password = generatePassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodePassword);
        user.setActive(isActive);
        user.setNotLocked(isNotLocked);
        user.setRole(getRoleEnumName(role).name());
        user.setAuthorities(getRoleEnumName(role).getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileUrl(username));
       // System.out.printf("/add before userRepository");
        userRepository.save(user);
        //System.out.printf("/add after userRepository");
        System.out.printf("Password ==> " + password);
        saveProfileImage(user,profileImage);
        return user;
    }

    @Override
    public User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isActive, boolean isNotLocked, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException {
        User currentUser = validateNewUsernameAndEmail(currentUsername,StringUtils.EMPTY,StringUtils.EMPTY );
        currentUser.setFirstName(newFirstName);
        currentUser.setLastName(newLastName);
        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);
        currentUser.setActive(isActive);
        currentUser.setNotLocked(isNotLocked);
        currentUser.setRole(getRoleEnumName(role).name());
        System.out.printf("/add before userRepository");
        userRepository.save(currentUser);
        System.out.printf("/add after userRepository");
        saveProfileImage(currentUser,profileImage);
        return currentUser;
    }

    @Override
    public void deleteUser(String username) {
          User user = userRepository.findUserByUsername(username);

        userRepository.deleteById(user.getId());
    }

    @Override
    public void resetPassword(String email) throws EmailNotFoundException, MessagingException {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new EmailNotFoundException(NO_USERNAME_FOUND_BY_EMAIL + email);

        }else{
           String password = generatePassword();
           user.setPassword(passwordEncoder.encode(password));
           userRepository.save(user);
           emailService.sendNewPasswordEmail(user.getFirstName(),password,user.getEmail());
        }

    }

    @Override
    public User updateProfileImage(String username, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException {
        User currentUser = validateNewUsernameAndEmail(username,null,null);
        saveProfileImage(currentUser,profileImage);
        return currentUser;
    }

    private void saveProfileImage(User user, MultipartFile profileImage) throws IOException {
        if (profileImage != null){
            Path userFolder  = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if(!Files.exists(userFolder)){
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + user.getUsername());
            }
            Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(),userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
            userRepository.save(user);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }

    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username + FORWARD_SLASH + username + DOT + JPG_EXTENSION).toString();
    }

    private Role getRoleEnumName(String role) {
     return Role.valueOf(role.toUpperCase());
    }


}
