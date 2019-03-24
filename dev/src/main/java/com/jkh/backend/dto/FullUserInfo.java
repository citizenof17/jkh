package com.jkh.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jkh.backend.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FullUserInfo implements Serializable {
    private String name;
    private Integer flatNumber;
    private String phone;
    private String login;
    private String email;
    private Status status;
    private Integer daysOverDefaultPeriodOfCountersSending;

    public FullUserInfo() {}

    public FullUserInfo(String name, Integer flatNumber, String phone, String login, String email,
                        Status status, Integer daysOverDefaultPeriodOfCountersSending) {
        this.name = name;
        this.flatNumber = flatNumber;
        this.phone = phone;
        this.login = login;
        this.email = email;
        this.status = status;
        this.daysOverDefaultPeriodOfCountersSending = daysOverDefaultPeriodOfCountersSending;
    }

    public FullUserInfo(String name, Integer flatNumber, String phone, String login, String email, Status status) {
        this.name = name;
        this.flatNumber = flatNumber;
        this.phone = phone;
        this.login = login;
        this.email = email;
        this.status = status;
    }
}
