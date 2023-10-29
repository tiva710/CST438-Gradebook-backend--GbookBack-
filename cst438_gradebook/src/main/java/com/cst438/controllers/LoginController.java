package com.cst438.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.User;
import com.cst438.domain.UserRepository;

public class LoginController {
  
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/login")
	public String login(@RequestBody User loginDetails) {
		User user = userRepository.findByEmail(loginDetails.getEmail());
		
		if(user!=null && user.getPassword().equals(loginDetails.getPassword())) {
			return "Login Successful";
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
		}
	}
}
