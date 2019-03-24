package com.jkh.backend.service;

import com.jkh.backend.dto.FullUserInfo;
import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.model.User;
import com.jkh.backend.dto.ResponseWrapperRegistrationValidator;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;

import java.util.List;

public interface UserService {

    void save(User user);
    void delete(User user);

    User findUserByLogin(String login);
    User findUserByRole(Role role);
    List<User> findUsersByStatus(Status status);
    List<User> findUsersByRoleAndStatus(Role role, Status status);

    ResponseWrapperRegistrationValidator register(User user);

    List<User> getAllUsers();

    void changeStatus(User user, Status status);

    ResponseWrapperStateWithMessages setStatusChangesBulk(List<List<FullUserInfo>> blocks);

}