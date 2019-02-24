package com.jkh.backend.service.implementation;

import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.repository.UserRepository;
import com.jkh.backend.service.FlatService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.service.validation.RegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private FlatService flatService;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public JSONObject register(User user) {
        JSONObject json = registrationValidator.validate(user);
        if (json.get("isOk").equals(true)) {
            user.setActive(false);
            user.setRole(Role.USER);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            boolean addNewFlat = flatService.addUserToFlat(user.getFlat(), user);
            save(user);
            log.info("New user with login=" + user.getLogin() + " was registered");
            if (addNewFlat) {
                log.info("New flat with number=" + user.getFlat().getNumber() + " was added");
            }
        }

        return json;
    }
}