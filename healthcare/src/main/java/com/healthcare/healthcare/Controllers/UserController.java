package com.healthcare.healthcare.Controllers;

import com.healthcare.healthcare.DTOs.UserDTO;
import com.healthcare.healthcare.Entities.UserT;
import com.healthcare.healthcare.Repositories.UserRepository;
import com.healthcare.healthcare.Services.UserService;
import com.healthcare.healthcare.Utility.BcryptUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private BcryptUtility bcryptUtility;
    private UserRepository userRepository;
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserT usert){
        return userService.registerUser(usert);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserT user){
        return userService.userLogin(user);
    }
}
