package com.healthcare.healthcare.Configuration;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String enteredEmail = authentication.getPrincipal().toString();
        String enteredPassword = authentication.getCredentials().toString();

//        userDetailsService.loadUserByUsername()

//        System.out.println(enteredPassword + enteredPassword);
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
