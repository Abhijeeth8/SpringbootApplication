package com.healthcare.healthcare.Services;

import com.healthcare.healthcare.DTOs.UserDTO;
import com.healthcare.healthcare.Entities.UserT;
import com.healthcare.healthcare.Repositories.UserRepository;
import com.healthcare.healthcare.Utility.BcryptUtility;
import com.healthcare.healthcare.Utility.JwtUtility;
import com.healthcare.healthcare.Utility.ModelMapperConfig;
import com.healthcare.healthcare.helpers.UserConverter;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    AuthenticationManager authenticationManager;

    private UserRepository userRepository;
    private BcryptUtility bcryptUtility;
    private UserConverter userConverter;
    private JwtUtility jwtUtility;

    public UserDTO registerUser(UserT user){
        String encodedPassword = bcryptUtility.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(user);
        UserT savedUser = userRepository.save(user);

        return userConverter.toUserDTO(savedUser);

    }

    public ResponseEntity<String> userLogin(UserT user) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);

        if(authenticate.isAuthenticated()){
            String jwtToken = jwtUtility.generateToken(user.getEmail());
            return ResponseEntity.ok().header("Authorization", "Bearer="+jwtToken).body("Success");
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }
}
