package com.jkh.backend.service.validation;

public interface ValidationMessages {

    String LOGIN_INCORRECT = "Некорректный логин";

    String NAME_INCORRECT = "Некорректное Ф.И.О.";

    String FLAT_NUMBER_INCORRECT = "Некорректный номер квартиры";

    String PHONE_INCORRECT = "Некорректный номер телефона";

    String EMAIL_INCORRECT = "Некорректный почтовый адрес";

    String LOGIN_NOT_UNIQUE = "Пользователь с таким логином уже существует";

    String PHONE_NOT_UNIQUE = "Пользователь с таким номером телефона уже существует";

    String EMAIL_NOT_UNIQUE = "Пользователь с таким адресом электронной почты уже существует";

    String PASSWORD_POLICY = "Длина пароля должна составлять не менее 8 символов, " +
            "где присутствуют по крайней мере одна строчная и одна прописная английские буквы, " +
            "одна цифра и один специальный символ ('_', '-', '!'), без пробелов";

    String OK = "ok";

    String INDICATIONS_LENGTH = "Неправильное количество показаний";

    String INDICATION_INCORRECT = "Некорректное показание счетчика";

    String INDICATION_DUPLICATE = "Найдено несколько одинаковых типов показаний";

    String REPORT_OPTIONS_DATE = "Указан некорректный период";

    String FORBIDDEN = "Отказано в доступе";

    String EMPTY_LIST = "Данных за указанный период нет";

    String INCORRECT_STATUS_CHANGE_IN_BLOCK = "Блок статусов некорректен в квартире ";

}
