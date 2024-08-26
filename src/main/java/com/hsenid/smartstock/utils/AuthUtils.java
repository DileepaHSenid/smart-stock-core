package com.hsenid.smartstock.utils;

import com.hsenid.smartstock.entity.User;
import com.hsenid.smartstock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    public String getCurrentUserIdFromAuthHeader(String authHeader) {
        // Extract the token from the Authorization header
        String token = authHeader.replace("Bearer ", "");

        // Extract the username or user ID from the token using JWTUtils
        String username = jwtUtils.extractUsername(token);

        // Retrieve the user from the repository using the username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Return the user ID
        return user.getId();
    }
}
