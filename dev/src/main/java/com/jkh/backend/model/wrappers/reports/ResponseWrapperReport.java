package com.jkh.backend.model.wrappers.reports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jkh.backend.service.validation.ValidationMessages;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@JsonIgnoreProperties("ok")
public class ResponseWrapperReport implements Serializable {
    private boolean isOk;
    private String message;

    public ResponseWrapperReport(String message) {
        this.message = message;
        this.isOk = this.message.equals(ValidationMessages.OK);
    }
}
