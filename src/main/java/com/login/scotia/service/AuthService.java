package com.login.scotia.service;

import java.util.Map;

public interface AuthService {
    Map<String, Object> login(String username, String password);
}
