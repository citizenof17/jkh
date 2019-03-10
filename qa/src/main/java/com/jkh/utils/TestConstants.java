package com.jkh.utils;

import com.jkh.BE.models.Flat;
import com.jkh.BE.models.RegisterBadResponse;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.models.RegisterResponse;

public interface TestConstants {

    String R = "Response";

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
                    "USER",
                    "Олег Раскин",
                    false
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
                        "USER",
                        "Екатерина Астровская",
                        false
                )
            }
    };

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
                            "login3",
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
}
