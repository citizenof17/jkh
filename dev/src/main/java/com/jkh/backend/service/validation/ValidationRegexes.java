package com.jkh.backend.service.validation;

public interface ValidationRegexes {

    String LOGIN_REGEX = "^[a-zA-ZА-Яа-я0-9]{3,}$";

    String NAME_REGEX = "^[a-zA-ZА-Яа-я ]{3,}$";

    String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!])(?=[0-9a-zA-Z_!-]+$).{8,}$";

    String PHONE_REGEX = "^\\+\\d{11}$";

    String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

}
