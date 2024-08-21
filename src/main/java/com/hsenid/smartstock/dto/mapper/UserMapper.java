package com.hsenid.smartstock.dto.mapper;


import com.hsenid.smartstock.dto.request.SupplierRequest;
import com.hsenid.smartstock.dto.request.UserRequest;
import com.hsenid.smartstock.dto.response.UserResponse;
import com.hsenid.smartstock.entity.Supplier;
import com.hsenid.smartstock.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getId());
        userResponse.setImg(user.getImg());
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
    public void updateUser(UserRequest userRequest, User user) {
        user.setUsername(userRequest.getUsername());
        user.setImg(userRequest.getImg());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
    }
}
