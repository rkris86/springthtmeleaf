package com.alvyl.springthymeleaf.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}