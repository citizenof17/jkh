package com.jkh.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ResponseWrapperStateWithMessages implements Serializable {
    Boolean isOk;
    List<String> messages;

    public ResponseWrapperStateWithMessages(Boolean isOk, List<String> messages) {
        this.isOk = isOk;
        this.messages = messages;
    }
}
