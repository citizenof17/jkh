package com.jkh.BE.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class IndicationResponse {

    boolean isOk;
    String messages;

    public IndicationResponse() {

    }

    public IndicationResponse(boolean isOk, String messages) {
        this.isOk = isOk;
        this.messages = messages;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndicationResponse that = (IndicationResponse) o;
        return isOk == that.isOk &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOk, messages);
    }

    @Override
    public String toString() {
        return "IndicationResponse{" +
                "isOk=" + isOk +
                ", messages='" + messages + '\'' +
                '}';
    }
}
