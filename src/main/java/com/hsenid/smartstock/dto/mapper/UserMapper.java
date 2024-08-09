package com.hsenid.smartstock.dto.mapper;


import com.hsenid.smartstock.dto.request.UserRequest;
import com.hsenid.smartstock.dto.response.UserResponse;
import com.hsenid.smartstock.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
    public User toUserRequest(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());

        return user;
}
}
