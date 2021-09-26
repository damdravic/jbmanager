package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.User;
import com.anaximel.bmanager.appsecurity.exception.domain.EmailExistException;
import com.anaximel.bmanager.appsecurity.exception.domain.EmailNotFoundException;
import com.anaximel.bmanager.appsecurity.exception.domain.UsernameExistException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register (String firstName,String lastName,String username,String email)
            throws UsernameExistException, EmailExistException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNotLocked,
                    boolean isActive, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNotLocked,
                    boolean isActive, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException;

    void deleteUser(Long id);

    void resetPassword( String email) throws EmailNotFoundException, MessagingException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException;



}
