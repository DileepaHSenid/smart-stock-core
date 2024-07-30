package com.hsenid.smartstock.controller;

import com.hsenid.smartstock.dto.ReqResDto;
import com.hsenid.smartstock.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqResDto> signUp(@RequestBody ReqResDto signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    @PostMapping("signin")
    public ResponseEntity<ReqResDto> signin(@RequestBody ReqResDto signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqResDto> refreshToken(@RequestBody ReqResDto refreshTokenRequest) {
        // Delegate the refresh token request to AuthService and return the response
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
