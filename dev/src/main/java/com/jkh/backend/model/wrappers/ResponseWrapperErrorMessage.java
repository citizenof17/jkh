package com.jkh.backend.model.wrappers;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ResponseWrapperErrorMessage implements Serializable {
    private String message;

    public ResponseWrapperErrorMessage(String message) {
        this.message = message;
    }
}
