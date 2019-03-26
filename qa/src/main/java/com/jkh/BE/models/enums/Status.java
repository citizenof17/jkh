package com.jkh.BE.models.enums;

public enum Status {

    UNVERIFIED("Неподтвержденный"),
    ACTIVE("Активный"),
    INACTIVE("Неактивный"),
    REMOVED("Удаленный");

    private String russianName;

    Status(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }
}
