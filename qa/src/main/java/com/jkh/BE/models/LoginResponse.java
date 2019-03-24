package com.jkh.BE.models;

public class LoginResponse extends RegisterResponse{

    public LoginResponse(RegisterResponse registerResponse) {
        super(registerResponse.getName(), registerResponse.getRole(), registerResponse.getStatus());
    }

    public LoginResponse() {

    }

    public LoginResponse(String name, String role, boolean status) {
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
