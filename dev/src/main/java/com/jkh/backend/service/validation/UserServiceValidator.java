package com.jkh.backend.service.validation;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import com.jkh.backend.dto.ResponseWrapperRegistrationValidator;
import com.jkh.backend.model.enums.Status;
import com.jkh.backend.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jkh.backend.service.validation.ValidationMessages.*;
import static com.jkh.backend.service.validation.ValidationRegexes.*;

@Service
public class UserServiceValidator {

    @Autowired
    private UserRepository userRepository;

    private String checkLogin(String login, MutableBoolean isOk) {
        if (login == null || !login.matches(LOGIN_REGEX)) {
            isOk.setFalse();
            return LOGIN_INCORRECT;
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

    private String checkName(String name, MutableBoolean isOk) {
        if (name == null || !name.matches(NAME_REGEX)) {
            isOk.setFalse();
            return NAME_INCORRECT;
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

        User user = userRepository.findUserByPhone(phone);
        if(user != null) {
            isOk.setFalse();
            return PHONE_NOT_UNIQUE;
        }

        return OK;
    }

    private String checkEmail(String email, MutableBoolean isOk) {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            isOk.setFalse();
            return EMAIL_INCORRECT;
        }

        User user = userRepository.findUserByEmail(email);
        if(user != null) {
            isOk.setFalse();
            return EMAIL_NOT_UNIQUE;
        }

        return OK;
    }

    public ResponseWrapperRegistrationValidator validate(User user) {
        MutableBoolean userDataIsOk = new MutableBoolean(true);
        return new ResponseWrapperRegistrationValidator(
                checkLogin(user.getLogin(), userDataIsOk),
                checkPassword(user.getPassword(), userDataIsOk),
                checkName(user.getName(), userDataIsOk),
                checkFlat(user.getFlat(), userDataIsOk),
                checkPhone(user.getPhone(), userDataIsOk),
                checkEmail(user.getEmail(), userDataIsOk),
                userDataIsOk.getValue());
    }

    public boolean compareStatusesIfChangeIsValid(Status cur, Status next) {
        return cur.equals(next) || next.equals(Status.REMOVED)
                || cur.equals(Status.UNVERIFIED) && next.equals(Status.ACTIVE)
                || cur.equals(Status.UNVERIFIED) && next.equals(Status.INACTIVE)
                || cur.equals(Status.ACTIVE) && next.equals(Status.INACTIVE)
                || cur.equals(Status.INACTIVE) && next.equals(Status.ACTIVE);

    }

    public boolean checkBlockForStatusChange(List<List<Status>> block) {
        if (block.isEmpty()) {
            return false;
        }

        boolean isOk = true;

        for (List<Status> statuses : block) {
            Status cur = statuses.get(0);
            Status next = statuses.get(1);

            isOk &= compareStatusesIfChangeIsValid(cur, next);
        }

        isOk &= (block.stream()
                .mapToInt(x -> (x.get(1).equals(Status.ACTIVE) || x.get(1).equals(Status.INACTIVE)) ? 1 : 0)
                .sum()) <= 1;

        return isOk;
    }
}
