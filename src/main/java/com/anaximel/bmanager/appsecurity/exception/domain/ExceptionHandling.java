package com.anaximel.bmanager.appsecurity.exception.domain;

import com.anaximel.bmanager.appsecurity.constant.SecurityConstant;
import com.anaximel.bmanager.appsecurity.domain.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public static final String ACCOUNT_LOCKED = "Account has been locked . Please contact administration";
    public static final String METHOD_NOT_ALLOWED = "This request method is not allowed on this endpoint";
    public static final String INTERNAL_SERVER_ERROR_MGS = "an error occurred while processing the request";
    public static final String INCORRECT_CREDENTIALS = "Username/password incorrect";
    public static final String ACCOUNT_DISABLED = "Your account has been disabled";
    public static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    public static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    public static final String ERROR_PATH="/error";

    @ExceptionHandler(DisabledException.class)
   public ResponseEntity<HttpResponse> accountDisabledException(){
       return createHttpResponse(HttpStatus.BAD_REQUEST,ACCOUNT_DISABLED);
   }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST,INCORRECT_CREDENTIALS);
    }
    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpResponse> userExistException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST, "This user exist");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> wrongMethodeException(){
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpResponse> emailExistException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST, "This email exist");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> noHandlerException(NoHandlerFoundException e){
        return createHttpResponse(HttpStatus.BAD_REQUEST,"This page was not found");
    }


    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException(){
        return createHttpResponse(HttpStatus.FORBIDDEN,ACCOUNT_LOCKED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> bException(Exception ex){
        LOGGER.error(ex.getMessage());
        LOGGER.error(String.valueOf(ex.getClass()));
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR,INTERNAL_SERVER_ERROR_MGS);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDenied(){
        return createHttpResponse(HttpStatus.FORBIDDEN,NOT_ENOUGH_PERMISSION);
    }


    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){

        HttpResponse httpResponse = new HttpResponse(httpStatus.value(),httpStatus,httpStatus.getReasonPhrase(),message.toUpperCase());

        return new ResponseEntity<>(httpResponse,httpStatus);




    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notfound404(){
        return createHttpResponse(HttpStatus.NOT_FOUND,"This page not exist");
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}








