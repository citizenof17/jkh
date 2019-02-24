package com.jkh.backend.service;

public interface SecurityService {

    String findLoggedInLogin();

    void login(String login, String password);
}