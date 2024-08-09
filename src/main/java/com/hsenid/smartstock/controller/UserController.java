package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.common.ApiResponse;
import com.hsenid.smartstock.common.StatusCode;
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
    private UserDetailService userDetailService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            List<UserResponse> users = userDetailService.getAllUsers()
                    .stream()
                    .map(userMapper::toUserResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(users)
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage())
            );
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest userRequest) {
        try {
            User user = userMapper.toUserRequest(userRequest);
            User createdUser = userDetailService.createUser(user);
            UserResponse userResponse = userMapper.toUserResponse(createdUser);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.forStatus(StatusCode.S0000)
                            .withMessage(StatusCode.S0000.getMessage())
                            .withPayload(userResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage(StatusCode.E5000.getMessage()));
        }
    }
}
