package com.jkh.FE.models;

import com.jkh.BE.models.Flat;
import com.jkh.BE.models.RegisterRequest;

import java.util.Objects;

public class RegisterUser extends RegisterRequest {

    private String confirmPassword;

    public RegisterUser(String login, String password, String phone, Flat flat, String name, String email, String confirmPassword) {
        super(login, password, phone, flat, name, email);
        this.confirmPassword = confirmPassword;
    }

    public RegisterUser(RegisterRequest that, String confirmPassword) {
        super(that.getLogin(), that.getPassword(), that.getPhone(), that.getFlat(), that.getName(), that.getEmail());
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RegisterUser that = (RegisterUser) o;
        return confirmPassword.equals(that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), confirmPassword);
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                super.toString() +
                "confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
