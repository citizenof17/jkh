package com.jkh.backend.dto;

import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ResponseWrapperUserAuth implements Serializable {
    private String name;
    private Role role;
    private Status status;

    public ResponseWrapperUserAuth(String name, Role role, Status status) {
        this.name = name;
        this.role = role;
        this.status = status;
    }
}
