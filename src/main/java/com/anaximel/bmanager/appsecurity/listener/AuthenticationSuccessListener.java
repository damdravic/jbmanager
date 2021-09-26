package com.anaximel.bmanager.appsecurity.listener;

import com.anaximel.bmanager.appsecurity.security.UserPrincipal;
import com.anaximel.bmanager.appsecurity.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {

    private LoginAttemptService loginAttemptService;

     @Autowired
    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent ev){

        Object principal =   ev.getAuthentication().getPrincipal();

        if(principal instanceof UserPrincipal){
         UserPrincipal user= (UserPrincipal) ev.getAuthentication().getPrincipal();
         loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());

        }

    }



}
