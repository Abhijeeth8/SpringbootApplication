package com.healthcare.healthcare;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class TestClass {
    @GetMapping("/")
    public String hello(){
        return "Hello! Welcome";
    }

}
