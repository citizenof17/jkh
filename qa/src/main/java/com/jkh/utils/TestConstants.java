package com.jkh.utils;

import com.jkh.BE.models.*;
import com.jkh.BE.models.enums.Status;

import java.util.Arrays;

public interface TestConstants {

    String R = "Response";

    Counter ELECTRICITY_COUNTER = new Counter(Counter.CounterType.ELECTRICITY);
    Counter COLD_WATER_COUNTER = new Counter(Counter.CounterType.COLD_WATER);
    Counter HOT_WATER_COUNTER = new Counter(Counter.CounterType.HOT_WATER);

    IndicationResponse CORRECT_INDICATION_RESPONSE = new IndicationResponse(true, "ok");

    Object[][] correctIndicationData = new Object[][]{
            {
                    Arrays.asList(
                            new IndicationRequest(
                                    ELECTRICITY_COUNTER,
                                    10
                            ),
                            new IndicationRequest(
                                    COLD_WATER_COUNTER,
                                    11
                            ),
                            new IndicationRequest(
                                    HOT_WATER_COUNTER,
                                    12
                            )
                    ),
                    CORRECT_INDICATION_RESPONSE
            }
    };

    Object[][] correctRegisterData = new Object[][]{
            {
                    new RegisterRequest(
                            "login1",
                            "Qwerty_1",
                            "+79996739856",
                            new Flat(12),
                            "Олег Раскин",
                            "oleg@mail.ru"
                    ),
                    new RegisterResponse(
                            "Олег Раскин",
                            "USER",
                            Status.UNVERIFIED
                    )
            },
            {
                    new RegisterRequest(
                            "login2",
                            "Qwerty_2",
                            "+79874147392",
                            new Flat(55),
                            "Екатерина Астровская",
                            "katya@gmail.com"
                    ),
                    new RegisterResponse(
                            "Екатерина Астровская",
                            "USER",
                            Status.UNVERIFIED
                    )
            },
            {
                    new RegisterRequest(
                            "login3",
                            "Qwerty_3",
                            "+79874147393",
                            new Flat(55),
                            "Валерий Островский",
                            "valera@gmail.com"
                    ),
                    new RegisterResponse(
                            "Валерий Островский",
                            "USER",
                            Status.UNVERIFIED
                    )
            }

    };

    RegisterRequest ADMIN = new RegisterRequest("Administrator", "Administrator_1", "+79995550000",
            new Flat(1000), "Администратор Великий Ужаснович", "Administrator@jkh.ru");

    RegisterRequest CORRECT_REGISTER_USER = (RegisterRequest) correctRegisterData[0][0];

    Object[][] incorrectRegisterData = new Object[][]{
            {
                    new RegisterRequest(
                            "login1",
                            "dsffdfQZ1_d",
                            "+79874147392",
                            new Flat(20),
                            "Олег Раскин",
                            "oleg@mail.ru"
                    ),
                    new RegisterBadResponse(
                            "Пользователь с таким логином уже существует",
                            "ok",
                            "Пользователь с таким номером телефона уже существует",
                            "ok",
                            "ok",
                            "Пользователь с таким адресом электронной почты уже существует"
                    )
            },
            {
                    new RegisterRequest(
                            "login6",
                            "dsffdf",
                            "+7999679856",
                            new Flat(-123),
                            "Олег Раскин",
                            "olegmail.ru"
                    ),
                    new RegisterBadResponse(
                            "ok",
                            "Длина пароля должна составлять не менее 8 символов, где присутствуют по крайней " +
                                    "мере одна строчная и одна прописная английские буквы, одна цифра и один специальный символ " +
                                    "('_', '-', '!'), без пробелов",
                            "Некорректный номер телефона",
                            "Некорректный номер квартиры",
                            "ok",
                            "Некорректный почтовый адрес"
                    )
            },
            {
                    new RegisterRequest(
                            "login4",
                            "dsffdf",
                            "+79996729856",
                            new Flat(-123),
                            "Олег Раскин",
                            "olegmail.ru"
                    ),
                    new RegisterBadResponse(
                            "ok",
                            "Длина пароля должна составлять не менее 8 символов, где присутствуют по крайней " +
                                    "мере одна строчная и одна прописная английские буквы, одна цифра и один специальный символ " +
                                    "('_', '-', '!'), без пробелов",
                            "ok",
                            "Некорректный номер квартиры",
                            "ok",
                            "Некорректный почтовый адрес"
                    )
            },
            {
                    new RegisterRequest(
                            "li2heb234 14h324 _ 235",
                            "dsffdfQQQZZZ___1",
                            "+79996729856",
                            new Flat(0),
                            "Олег Раскин",
                            "oleg@mail,ru"
                    ),
                    new RegisterBadResponse(
                            "ok",
                            "ok",
                            "ok",
                            "Некорректный номер квартиры",
                            "ok",
                            "Некорректный почтовый адрес"
                    )
            }
    };
    //jkh url addresses
    String LOGIN_PAGE_ADDRESS = "/login";
    String REGISTRATION_PAGE_ADDRESS = "/register";
    String HOME_PAGE_ADDRESS = "/home";
    String ADMIN_PAGE_ADDRESS = "/admin";
    String EDIT_INHABITANTS_ADDRESS = "/edit_inhabitants";
    String DID_NOT_SEND_ADDRESS = "/did_not_send";
    String NEWCOMERS_ADDRESS = "/newcomers";

    Object[] incorrectLoginData = new String[]{"!0wrt", "12", "My login"};
    Object[] incorrectNameData = new String[]{"I", "Александр 1"};
    Object[] incorrectFlatData = new Integer[]{-10, 0};
    Object[] incorrectEmailData = new String[]{"!sf@df s", "olegmail.ru", "12@m"};
    Object[] incorrectPhoneData = new String[]{"+7999999999", "99999999999", "+791111111111"};
    Object[] incorrectPasswordData = new String[]{"12Qwer!", "!2314324_ddfs"};

    Object[][] incorrectStatusesForFlat55Data = new Object[][]{
            {Arrays.asList(Status.ACTIVE, Status.ACTIVE)},
            {Arrays.asList(Status.ACTIVE, Status.INACTIVE)},
            {Arrays.asList(Status.INACTIVE, Status.INACTIVE)}
    };

    String NOT_FOUND_FLAT = "1000";

    String CORRECT_FORMAT_LOGIN = "Victor1";
    String CORRECT_FORMAT_PASSWORD = "Qwerty_1";

    String HOME_PAGE_WELCOME_TITLE = "Добро пожаловать, %s!";
    String HOME_ADMIN_NAME = "Имя: %s";
    String HOME_ADMIN_EMAIL = "Электронная почта: %s";
    String HOME_ADMIN_PHONE = "Телефон: %s";

    String UNCONFIRMED_PASSWORD = "Unconfirmed_1";
    String ADMIN_PAGE_WELCOME_TITLE = "Администратор %s";
}
