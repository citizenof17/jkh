package com.jkh.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestWrapperSendIndication {
    private User user;
    private List<Indication> indicationList;
}
