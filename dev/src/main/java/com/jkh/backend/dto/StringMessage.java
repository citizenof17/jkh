package com.jkh.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class StringMessage implements Serializable {
    private String message;

    public StringMessage(String message) {
        this.message = message;
    }
}

