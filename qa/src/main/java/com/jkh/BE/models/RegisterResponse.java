package com.jkh.BE.models;

import java.util.Objects;

public class RegisterResponse {

    private String role;
    private String name;
    private Boolean status;

    public RegisterResponse() {
    }

    public RegisterResponse(String role, String name, Boolean status) {
        this.role = role;
        this.name = name;
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterResponse that = (RegisterResponse) o;
        return Objects.equals(role, that.role) && Objects.equals(name, that.name) &&
               Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, name, status);
    }

    @Override
    public String toString() {
        return "RegisterResponse{" + "role='" + role + '\'' + ", name='" + name + '\'' + ", status=" + status +
               '}';
    }
}
