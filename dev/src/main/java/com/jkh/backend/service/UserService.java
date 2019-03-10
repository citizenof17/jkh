package com.jkh.backend.service;

import com.jkh.backend.model.User;
import com.jkh.backend.model.wrappers.ResponseWrapperRegistrationValidator;

public interface UserService {

    void save(User user);

    User findUserByLogin(String login);

    ResponseWrapperRegistrationValidator register(User user);

}