package com.jkh.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ResponseWrapperRegistrationValidator implements Serializable {
    private String login;
    private String password;
    private String name;
    private String flat;
    private String phone;
    private String email;

    @JsonIgnore
    private boolean isOk;

    public ResponseWrapperRegistrationValidator(
            String login, String password, String name, String flat, String phone, String email, boolean isOk) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.flat = flat;
        this.phone = phone;
        this.email = email;
        this.isOk = isOk;
    }
}
