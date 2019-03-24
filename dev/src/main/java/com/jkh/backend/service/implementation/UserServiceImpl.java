package com.jkh.backend.service.implementation;

import com.jkh.backend.dto.FullUserInfo;
import com.jkh.backend.dto.ResponseWrapperRegistrationValidator;
import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import com.jkh.backend.repository.UserRepository;
import com.jkh.backend.service.FlatService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.service.validation.UserServiceValidator;
import com.jkh.backend.service.validation.ValidationMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserServiceValidator userServiceValidator;

    @Autowired
    private FlatService flatService;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        Flat flat = user.getFlat();
        userRepository.delete(user);
        if (flat.getUserSet().isEmpty()) {
            flatService.delete(flat);
        }
    }


    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User findUserByRole(Role role) {
        return userRepository.findUserByRole(role);
    }

    @Override
    public List<User> findUsersByStatus(Status status) {
        return userRepository.findUsersByStatus(status);
    }

    @Override
    public List<User> findUsersByRoleAndStatus(Role role, Status status) {
        return userRepository.findUsersByRoleAndStatus(role, status);
    }

    @Override
    public ResponseWrapperRegistrationValidator register(User user) {
        ResponseWrapperRegistrationValidator json = userServiceValidator.validate(user);
        if (json.isOk()) {
            user.setStatus(Status.UNVERIFIED);
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void changeStatus(User user, Status desiredStatus) {
        if (user.getStatus().equals(Status.UNVERIFIED) && desiredStatus.equals(Status.REMOVED)) {
            delete(user);
        }
        else {
            user.setStatus(desiredStatus);
            save(user);
        }
    }

    @Override
    public ResponseWrapperStateWithMessages setStatusChangesBulk(List<List<FullUserInfo>> blocks) {
        ResponseWrapperStateWithMessages ans = checkBlocksForStatusChange(blocks);

        if (ans.getIsOk()) {
            blocks.forEach(block -> block.forEach(x -> changeStatus(findUserByLogin(x.getLogin()), x.getStatus())));
        }

        return ans;
    }

    private ResponseWrapperStateWithMessages checkBlocksForStatusChange(List<List<FullUserInfo>> blocks) {
        boolean isOk = true;

        List<String> messages = new ArrayList<>();

        for (List<FullUserInfo> block : blocks) {
            boolean checkBlock = checkBlockForStatusChange(block);
            if (!checkBlock) {
                messages.add(ValidationMessages.INCORRECT_STATUS_CHANGE_IN_BLOCK + block.get(0).getFlatNumber());
            }

            isOk &= checkBlock;
        }

        return new ResponseWrapperStateWithMessages(isOk, messages);
    }


    private boolean checkBlockForStatusChange(List<FullUserInfo> block) {

        List<List<Status>> usersWithStatuses = block.stream()
                .map(x -> new ArrayList<>(Arrays.asList(findUserByLogin(x.getLogin()).getStatus(), x.getStatus())))
                .collect(Collectors.toList());

        return userServiceValidator.checkBlockForStatusChange(usersWithStatuses);

    }
}