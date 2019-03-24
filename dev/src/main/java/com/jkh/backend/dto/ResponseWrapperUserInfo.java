package com.jkh.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapperUserInfo extends ResponseWrapperUserAuth implements Serializable {

    private Integer daysOverDefaultPeriodOfCountersSending;
    private Integer defaultPeriodBetweenSendings;
    private Integer countNewcomers;
    private Integer countWhoDidNotSend;

    public ResponseWrapperUserInfo(String name, Role role, Status status,
                                   Integer daysOverDefaultPeriodOfCountersSending) {

        super(name, role, status);
        this.daysOverDefaultPeriodOfCountersSending = daysOverDefaultPeriodOfCountersSending;
    }

    public ResponseWrapperUserInfo(String name, Role role, Status status,
                                   Integer defaultPeriodBetweenSendings,
                                   Integer countNewcomers, Integer countWhoDidNotSend) {

        super(name, role, status);
        this.defaultPeriodBetweenSendings = defaultPeriodBetweenSendings;
        this.countNewcomers = countNewcomers;
        this.countWhoDidNotSend = countWhoDidNotSend;
    }
}
