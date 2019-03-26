package com.jkh.BE.models;

import com.jkh.BE.models.enums.Status;

public class LoginResponse extends RegisterResponse {

    public LoginResponse(RegisterResponse registerResponse) {
        super(registerResponse.getName(), registerResponse.getRole(), registerResponse.getStatus());
    }

    public LoginResponse() {

    }

    public LoginResponse(String name, String role, Status status) {
        super(name, role, status);
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "name='" + super.getName() + '\'' +
                ", role='" + super.getRole() + '\'' +
                ", status=" + super.getStatus() +
                '}';
    }
}
