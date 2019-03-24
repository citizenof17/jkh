package com.jkh.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class AdminContacts implements Serializable {
    private String name;
    private String email;
    private String phone;

    public AdminContacts(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
