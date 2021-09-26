package com.anaximel.bmanager.appsecurity.listener;

import com.anaximel.bmanager.appsecurity.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener {

    private LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationFailureListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }


    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent events){

        Object principal = events.getAuthentication().getPrincipal();
        if(principal instanceof  String ){
            String username = (String) events.getAuthentication().getPrincipal();
            loginAttemptService.addUserToLoginAttemptCache(username);

        }
    }




}
