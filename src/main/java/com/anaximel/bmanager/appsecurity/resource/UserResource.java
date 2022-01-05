package com.anaximel.bmanager.appsecurity.resource;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.anaximel.bmanager.appsecurity.domain.HttpResponse;
import com.anaximel.bmanager.appsecurity.domain.User;
import com.anaximel.bmanager.appsecurity.exception.domain.EmailExistException;
import com.anaximel.bmanager.appsecurity.exception.domain.EmailNotFoundException;
import com.anaximel.bmanager.appsecurity.exception.domain.ExceptionHandling;
import com.anaximel.bmanager.appsecurity.exception.domain.UsernameExistException;
import com.anaximel.bmanager.appsecurity.security.UserPrincipal;
import com.anaximel.bmanager.appsecurity.service.UserService;
import com.anaximel.bmanager.appsecurity.utility.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.websocket.server.PathParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import static com.anaximel.bmanager.appsecurity.constant.FileConstant.*;
import static com.anaximel.bmanager.appsecurity.constant.SecurityConstant.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping(path =  {"/","/user"})
@CrossOrigin(origins = "*")
public class UserResource extends ExceptionHandling {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserResource(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UsernameExistException,
                                                                            EmailExistException,
                                                                             MessagingException {
        System.out.println(user.getFirstName() + user.getLastName()+ user.getUsername() + user.getEmail());
        User newUser = userService.register(user.getFirstName(), user.getLastName(),user.getUsername(),user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNotLocked") String isNotLocked,
                                           @RequestParam(value = "profileImage",required = false) MultipartFile profileImage) throws UsernameExistException,
                                                                                                                         EmailExistException, IOException {


        System.out.printf("1" + firstName);
        System.out.printf("2" + lastName);
        System.out.printf("3" + username);
        System.out.printf("4" + email);
        System.out.printf("5" + role);
        System.out.printf("6 " + isActive);
        User user  = userService.addNewUser(firstName, lastName, username, email, role, Boolean.parseBoolean(isActive), Boolean.parseBoolean(isNotLocked),
                profileImage);
        System.out.printf("/add after user.service");
        return new ResponseEntity<>(user, OK);

    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam("currentUser") String currentUser,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String TisActive,
                                           @RequestParam("isNotLocked") String TisNotLocked,
                                           @RequestParam(value = "profileImage",required = false) MultipartFile profileImage) throws UsernameExistException,
            EmailExistException, IOException {
        boolean isActive = Boolean.parseBoolean(TisActive);
        boolean isNotLocked = Boolean.parseBoolean(TisNotLocked);
        System.out.printf("active " + isActive);
        System.out.printf("locked " + isNotLocked);
        User updatedUser  = userService.updateUser(currentUser,firstName, lastName, username, email, role,isActive, isNotLocked,
                profileImage);
        return new ResponseEntity<>(updatedUser, OK);

    }


    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){

        User user =  userService.findUserByUsername(username);
        return new ResponseEntity<>(user,OK);


    }

    @PostMapping("/updateProfileImage")
    public ResponseEntity<User> updateProfileImage(
                                           @RequestParam("username") String username,
                                           @RequestParam(value = "profileImage") MultipartFile profileImage) throws UsernameExistException,
            EmailExistException, IOException {

        User user  = userService.updateProfileImage( username, profileImage);
        return new ResponseEntity<>(user, OK);
    }


    @GetMapping(path = "/image/{username}/{fileName}",produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathParam("username") String username,@PathParam("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
    }

    @GetMapping(path = "/image/profile/{username}",produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathParam("username") String username) throws IOException {


        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(InputStream inputStream = url.openStream()){
            int bytesRead;
            byte[] chunk = new byte[1024];
            while((bytesRead = inputStream.read(chunk))> 0 ){
                byteArrayOutputStream.write(chunk,0,bytesRead);
            }
        }

        return byteArrayOutputStream.toByteArray();
    }



    @GetMapping("/list")

    public ResponseEntity<List<User>> getAllUser(){
         List<User> users = userService.getUsers();
         return new ResponseEntity<>(users,OK);
    }


    @GetMapping("/resetPassword/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws EmailNotFoundException, MessagingException {

        userService.resetPassword(email);
        return response(OK,"Email sent to : " + email);

    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
       public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
        return response(OK,"User deleted successfully");
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        HttpResponse body = new HttpResponse(httpStatus.value(),httpStatus,httpStatus.getReasonPhrase().toUpperCase(),message.toUpperCase() );
        return new ResponseEntity<>(body,httpStatus);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) throws
                                  UsernameExistException, EmailExistException {
        System.out.println(user.getUsername() +"      "+ user.getPassword());
      authenticate(user.getUsername(),user.getPassword());

      User loginUser =  userService.findUserByUsername(user.getUsername());
        System.out.println(loginUser.getUsername() +"    --   "+ loginUser.getPassword());
      UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        System.out.println(userPrincipal);

      HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser,jwtHeader,OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        System.out.println("in get header");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER,jwtTokenProvider.generateJwtToken(userPrincipal));
        System.out.println(httpHeaders);
        return httpHeaders;
    }

    private void authenticate(String username, String password) {
        System.out.println("-- in authenticate -- ");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }


}
