package com.jkh.backend.dto;

import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseWrapperUserInfo extends ResponseWrapperUserAuth implements Serializable {

    private Integer daysOverDefaultPeriodOfCountersSending;

    public ResponseWrapperUserInfo(String name, Role role, Status status, Integer days) {
        super(name, role, status);
        this.daysOverDefaultPeriodOfCountersSending = days;
    }
}
