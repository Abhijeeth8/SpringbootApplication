package com.healthcare.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HealthcareApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HealthcareApplication.class, args);
	}

}
