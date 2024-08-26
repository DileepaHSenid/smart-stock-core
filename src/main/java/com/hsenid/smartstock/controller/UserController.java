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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id) {
        try {
            userDetailService.deleteUser(id);
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.S0000)
                    .withMessage("user deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.forStatus(StatusCode.E5000)
                    .withMessage(e.getMessage()));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
        try {
            Optional<User> existingUserOpt =userDetailService.getUserById(id);
            if (existingUserOpt.isPresent()) {
                User existingUser= existingUserOpt.get();
                userMapper.updateUser(userRequest, existingUser);
                User updatedUser = userDetailService.updateUser(existingUser);
                UserResponse userResponse = userMapper.toUserResponse(updatedUser);
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.S0000)
                                .withMessage("User updated successfully")
                                .withPayload(userResponse)
                );
            } else {
                return ResponseEntity.ok(
                        ApiResponse.forStatus(StatusCode.E4004)
                                .withMessage("User not found")
                );
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E4001)
                            .withMessage(StatusCode.E4001.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ApiResponse.forStatus(StatusCode.E5000)
                            .withMessage("An error occurred while updating the User")
            );
        }
    }
}
