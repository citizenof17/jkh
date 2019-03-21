package com.jkh.BE.models;

import java.util.Objects;

public class RegisterRequest {

    private String login;
    private String password;
    private String phone;
    private Flat flat;
    private String name;
    private String email;

    public RegisterRequest() {
    }

    public RegisterRequest(String login, String password, String phone, Flat flat, String name, String email) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.flat = flat;
        this.name = name;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterRequest that = (RegisterRequest) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone) && Objects.equals(flat, that.flat) &&
                Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, phone, flat, name, email);
    }

    @Override
    public String toString() {
        return "RegisterRequest{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", phone='" + phone +
                '\'' + ", flat=" + flat + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
