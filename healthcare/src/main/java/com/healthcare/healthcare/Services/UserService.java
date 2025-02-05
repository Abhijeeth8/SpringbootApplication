package com.healthcare.healthcare.Services;

import com.healthcare.healthcare.DTOs.UserDTO;
import com.healthcare.healthcare.Entities.UserT;
import com.healthcare.healthcare.Repositories.UserRepository;
import com.healthcare.healthcare.Utility.BcryptUtility;
import com.healthcare.healthcare.Utility.ModelMapperConfig;
import com.healthcare.healthcare.helpers.UserConverter;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    public UserDTO registerUser(UserT user){
        String encodedPassword = bcryptUtility.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(user);
        UserT savedUser = userRepository.save(user);

        return userConverter.toUserDTO(savedUser);

    }

    public String userLogin(UserT user) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);

        if(authenticate.isAuthenticated()){
            return "success";
        }
        return "Failed";
    }
}
