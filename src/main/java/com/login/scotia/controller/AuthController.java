package com.login.scotia.controller;

import com.login.scotia.dto.UserCredentials;
import com.login.scotia.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserCredentials credentials) {
        return ResponseEntity.ok(authService.login(credentials.getUsername(), credentials.getPassword()));
    }
}
