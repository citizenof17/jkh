package com.jkh.backend.service;

import com.jkh.backend.dto.FullUserInfo;
import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.model.User;
import com.jkh.backend.dto.ResponseWrapperRegistrationValidator;
import com.jkh.backend.model.enums.Status;

import java.util.List;

public interface UserService {

    void save(User user);

    User findUserByLogin(String login);

    ResponseWrapperRegistrationValidator register(User user);

    List<User> getAllUsers();

    void changeStatus(User user, Status status);

    ResponseWrapperStateWithMessages setStatusChangesBulk(List<List<FullUserInfo>> blocks);

}