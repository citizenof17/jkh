package com.jkh.backend.service.validation;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import com.jkh.backend.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.jkh.backend.service.validation.ValidationMessages.*;

@Service
public class RegistrationValidator {

    @Autowired
    private UserRepository userRepository;

    private String checkLogin(String login, MutableBoolean isOk) {
        if (login == null || login.length() < 3) {
            isOk.setFalse();
            return LOGIN_LENGTH;
        }

        User user = userRepository.findUserByLogin(login);
        if(user != null) {
            isOk.setFalse();
            return LOGIN_NOT_UNIQUE;
        }
        return OK;
    }

    private String checkPassword(String password, MutableBoolean isOk) {
        if (password == null || !password.matches(PASSWORD_REGEX)) {
            isOk.setFalse();
            return PASSWORD_POLICY;
        }

        return OK;
    }

    private String checkNotEmpty(String field, MutableBoolean isOk) {
        if (StringUtils.isBlank(field)) {
            isOk.setFalse();
            return FIELD_IS_EMPTY;
        }

        return OK;
    }

    private String checkFlat(Flat flat, MutableBoolean isOk) {
        if (flat == null || flat.getNumber() == null || flat.getNumber() <= 0) {
            isOk.setFalse();
            return FLAT_NUMBER_INCORRECT;
        }

        return OK;
    }

    private String checkPhone(String phone, MutableBoolean isOk) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            isOk.setFalse();
            return PHONE_INCORRECT;
        }

        return OK;
    }

    private String checkEmail(String email, MutableBoolean isOk) {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            isOk.setFalse();
            return EMAIL_INCORRECT;
        }

        return OK;
    }


    public JSONObject validate(User user) {
        MutableBoolean userDataIsOk = new MutableBoolean(true);

        JSONObject json = new JSONObject();

        json.put("login", checkLogin(user.getLogin(), userDataIsOk));
        json.put("password", checkPassword(user.getPassword(), userDataIsOk));
        json.put("name", checkNotEmpty(user.getName(), userDataIsOk));
        json.put("flat", checkFlat(user.getFlat(), userDataIsOk));
        json.put("phone", checkPhone(user.getPhone(), userDataIsOk));
        json.put("email", checkEmail(user.getEmail(), userDataIsOk));
        json.put("isOk", userDataIsOk.getValue());

        return json;
    }
}
