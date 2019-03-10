package com.jkh.backend.service.validation;

public interface ValidationMessages {

    String LOGIN_LENGTH = "Длина логина должна составлять не менее 3 символов";

    String LOGIN_NOT_UNIQUE = "Пользователь с таким логином уже существует";

    String PHONE_NOT_UNIQUE = "Пользователь с таким номером телефона уже существует";

    String EMAIL_NOT_UNIQUE = "Пользователь с таким адресом электронной почты уже существует";

    String PASSWORD_POLICY = "Длина пароля должна составлять не менее 8 символов, " +
            "где присутствуют по крайней мере одна строчная и одна прописная английские буквы, " +
            "одна цифра и один специальный символ ('_', '-', '!'), без пробелов";

    String FIELD_IS_EMPTY = "Поле не должно быть пустым";

    String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!])(?=\\S+$).{8,}$";

    String FLAT_NUMBER_INCORRECT = "Некорректный номер квартиры";

    String PHONE_REGEX = "^\\+\\d{11}$";

    String PHONE_INCORRECT = "Некорректный номер телефона";

    String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    String EMAIL_INCORRECT = "Некорректный почтовый адрес";

    String OK = "ok";

    String INDICATIONS_LENGTH = "Неправильное количество показаний";

    String INDICATION_INCORRECT = "Некорректное показание счетчика";

    String INDICATION_DUPLICATE = "Найдено несколько одинаковых типов показаний";

    String REPORT_OPTIONS_DATE = "Указан некорректный период";

    String FORBIDDEN = "Отказано в доступе";

    String DID_NOT_SEND_ALL_TIME = "Бесконечный список";

    String EMPTY_LIST = "Данных за указанный период нет";

}
