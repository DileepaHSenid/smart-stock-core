package com.IMS.IMS.controller;

import com.IMS.IMS.dto.mapper.UserMapper;
import com.IMS.IMS.dto.request.UserRequest;
import com.IMS.IMS.dto.response.UserResponse;
import com.IMS.IMS.model.User;
import com.IMS.IMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @PostMapping("/CreateUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.toUserRequest(userRequest);
        User createdUser = userService.createUser(user);
        UserResponse userResponse = userMapper.toUserResponse(createdUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
