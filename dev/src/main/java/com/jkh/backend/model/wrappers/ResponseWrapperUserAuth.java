package com.jkh.backend.model.wrappers;

import com.jkh.backend.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ResponseWrapperUserAuth implements Serializable {
    private String name;
    private Role role;
    private boolean status;

    public ResponseWrapperUserAuth(String name, Role role, boolean status) {
        this.name = name;
        this.role = role;
        this.status = status;
    }
}
