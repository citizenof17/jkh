package com.jkh.backend.service;

import com.jkh.backend.model.User;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    void save(User user);

    User findUserByLogin(String login);

    JSONObject register(User user);

}