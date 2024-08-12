//package com.hsenid.smartstock.service;
//
//import com.hsenid.smartstock.dto.ReqResDto;
//import com.hsenid.smartstock.entity.User;
//import com.hsenid.smartstock.repository.UserRepository;
//import com.hsenid.smartstock.utils.JWTUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Optional;
//
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    public ReqResDto signUp (ReqResDto registration) {
//
//        ReqResDto reqResDto = new ReqResDto();
//        if (userRepository.findByUsername(registration.getUsername()).isPresent()){
//            reqResDto.setStatusCode(400);
//            reqResDto.setMessage("Email is already registered, Please Signin");
//            return reqResDto;
//        }
//
//        User user = new User();
//        user.setUsername(registration.getUsername());
//        user.setPassword(registration.getPassword());
//        user.setFirstName(registration.getFirstName());
//        user.setLastName(registration.getLastName());
//        user.setRole(registration.getRole());
//
//        User savedUser =userRepository.save(user);
//
//        if (savedUser != null && savedUser.getId() !=null) {
//            reqResDto.setUser(savedUser);
//            reqResDto.setMessage("User has been registered successfully");
//            reqResDto.setStatusCode(200);
//        }else {
//            reqResDto.setStatusCode(500);
//            reqResDto.setMessage("Failed to register user");
//        }
//        return reqResDto;
//    }
//
//    public ReqResDto signIn (ReqResDto signInRequest){
//        ReqResDto reqResDto = new ReqResDto();
//        try{
//            String userName = signInRequest.getUsername();
//            String password = signInRequest.getPassword();
//
//            // Check if the user exists in the database
//            Optional<User> userOptional = userRepository.findByUsername(userName);
//            if (userOptional.isEmpty()) {
//                reqResDto.setStatusCode(401);
//                reqResDto.setError("Invalid email");
//                return reqResDto;
//            }
//
//            User user = userOptional.get();
//
//            // Check if the provided password matches the stored password
//            if (!passwordEncoder.matches(password, user.getPassword())) {
//                reqResDto.setStatusCode(401);
//                reqResDto.setError("Invalid password");
//                return reqResDto;
//            }
//
//            // Authenticate the user credentials
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
//
//            // Generate JWT
//            String jwt = jwtUtils.generateToken(user);
//
//            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
//
//            reqResDto.setStatusCode(200);
//            reqResDto.setToken(jwt);
//            reqResDto.setRefreshToken(refreshToken);
//            reqResDto.setExpirationTime("24Hr");
//            reqResDto.setMessage("User has been logged in successfully");
//
//        }catch (Exception e) {
//            reqResDto.setStatusCode(500);
//            reqResDto.setError("Error during login: " + e.getMessage());
//        }
//        return reqResDto;
//    }
//
//    public ReqResDto refreshToken(ReqResDto refreshTokenRequest) {
//        ReqResDto reqRes = new ReqResDto();
//        try {
//            String username = jwtUtils.extractUsername(refreshTokenRequest.getToken());
//            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
//
//            // Validate the refresh token
//            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
//                String newJwt = jwtUtils.generateToken(user);
//
//                reqRes.setStatusCode(200);
//                reqRes.setToken(newJwt);
//                reqRes.setRefreshToken(refreshTokenRequest.getToken());
//                reqRes.setExpirationTime("24Hr");
//                reqRes.setMessage("Token has been refreshed successfully");
//            } else {
//                reqRes.setStatusCode(401);
//                reqRes.setMessage("Invalid refresh token");
//            }
//        } catch (Exception e) {
//            reqRes.setStatusCode(500);
//            reqRes.setError("Error during token refresh: " + e.getMessage());
//        }
//        return reqRes;
//    }
//
//}


package com.hsenid.smartstock.service;

import com.hsenid.smartstock.dto.ReqResDto;
import com.hsenid.smartstock.entity.User;
import com.hsenid.smartstock.repository.UserRepository;
import com.hsenid.smartstock.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    );

    // Handles user registration
    public ReqResDto signUp(ReqResDto registrationRequest) {
        ReqResDto reqResDto = new ReqResDto();
        try {
            if (!isValidEmail(registrationRequest.getUsername())) {
                reqResDto.setStatusCode(400);
                reqResDto.setMessage("Invalid email format");
                return reqResDto;
            }

            // Check if the email is already registered
            if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
                reqResDto.setStatusCode(400);
                reqResDto.setMessage("Email is already in use");
                return reqResDto;
            }

            // Create a new user object and set its properties
            User user = new User();
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());

            // Save the user to the database
            User savedUser = userRepository.save(user);

            // Check if the user was saved successfully
            if (savedUser != null && savedUser.getId() != null) {
                reqResDto.setUser(savedUser);
                reqResDto.setMessage("User has been registered successfully");
                reqResDto.setStatusCode(200);
            } else {
                reqResDto.setStatusCode(500);
                reqResDto.setMessage("Failed to register user");
            }

        } catch (Exception e) {
            reqResDto.setStatusCode(500);
            reqResDto.setError("Error during registration: " + e.getMessage());
        }
        return reqResDto;
    }

    // Handles user login
    public ReqResDto signIn(ReqResDto signInRequest) {
        ReqResDto reqResDto = new ReqResDto();
        try {
            String email = signInRequest.getUsername();
            String password = signInRequest.getPassword();

            if (!isValidEmail(email)) {
                reqResDto.setStatusCode(400);
                reqResDto.setError("Invalid email format");
                return reqResDto;
            }

            // Check if the user exists in the database
            Optional<User> userOptional = userRepository.findByUsername(email);
            if (userOptional.isEmpty()) {
                reqResDto.setStatusCode(401);
                reqResDto.setError("Invalid email");
                return reqResDto;
            }

            User user = userOptional.get();

            // Check if the provided password matches the stored password
            if (!passwordEncoder.matches(password, user.getPassword())) {
                reqResDto.setStatusCode(401);
                reqResDto.setError("Invalid password");
                return reqResDto;
            }

            // Authenticate the user credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            // Generate JWT and refresh token
            String jwt = jwtUtils.generateToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            reqResDto.setStatusCode(200);
            reqResDto.setToken(jwt);
            reqResDto.setRefreshToken(refreshToken);
            reqResDto.setExpirationTime("24Hr");
            reqResDto.setMessage("User has been logged in successfully");

        } catch (Exception e) {
            reqResDto.setStatusCode(500);
            reqResDto.setError("Error during login: " + e.getMessage());
        }
        return reqResDto;
    }

    // Handles token refresh
    public ReqResDto refreshToken(ReqResDto refreshTokenRequest) {
        ReqResDto reqResDto = new ReqResDto();
        try {
            String email = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            User user = userRepository.findByUsername(email).orElseThrow(() -> new RuntimeException("User not found"));

            // Validate the refresh token
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
                String newJwt = jwtUtils.generateToken(user);

                reqResDto.setStatusCode(200);
                reqResDto.setToken(newJwt);
                reqResDto.setRefreshToken(refreshTokenRequest.getToken());
                reqResDto.setExpirationTime("24Hr");
                reqResDto.setMessage("Token has been refreshed successfully");
            } else {
                reqResDto.setStatusCode(401);
                reqResDto.setMessage("Invalid refresh token");
            }
        } catch (Exception e) {
            reqResDto.setStatusCode(500);
            reqResDto.setError("Error during token refresh: " + e.getMessage());
        }
        return reqResDto;
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).find();
    }
}

