package com.healthcare.healthcare.Utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptUtility {

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
