package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investment.config.securityconfig.jwt.JwtUtil;
import com.investment.pojos.User;
import com.investment.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User user) {
    	System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        User savedUser = userRepository.save(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    
    
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Find the user by email
        User foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    
}

