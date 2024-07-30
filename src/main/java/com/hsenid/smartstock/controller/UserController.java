package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.dto.mapper.UserMapper;
import com.hsenid.smartstock.dto.request.UserRequest;
import com.hsenid.smartstock.dto.response.UserResponse;
import com.hsenid.smartstock.entity.User;
import com.hsenid.smartstock.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDetailService userdetailService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/Users")
    public List<UserResponse> getAllUsers() {
        return userdetailService.getAllUsers()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @PostMapping("/CreateUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.toUserRequest(userRequest);
        User createdUser = userdetailService.createUser(user);
        UserResponse userResponse = userMapper.toUserResponse(createdUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
