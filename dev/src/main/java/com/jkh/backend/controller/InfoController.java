package com.jkh.backend.controller;

import com.jkh.backend.dto.AdminContacts;
import com.jkh.backend.dto.ResponseWrapperUserInfo;
import com.jkh.backend.dto.StringMessage;
import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import com.jkh.backend.service.PeriodCalculatorService;
import com.jkh.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private static final String USER_INFO_ENDPOINT = "/userInfo";
    private static final String GET_ADMIN_CONTACTS = "/getAdminContacts";

    private static final String USER_NOT_FOUND = "Пользователь не найден";

    @Autowired
    private UserService userService;

    @Autowired
    private PeriodCalculatorService periodCalculatorService;

    @ResponseBody
    @RequestMapping(value = USER_INFO_ENDPOINT)
    public ResponseEntity<Object> getUserInfo() {
        try {
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findUserByLogin(login);
            ResponseWrapperUserInfo userInfo;
            if (user.getRole().equals(Role.ADMIN)) {
                userInfo = new ResponseWrapperUserInfo(
                        user.getName(),
                        user.getRole(),
                        user.getStatus(),
                        periodCalculatorService.getDefaultPeriodBetweenSendings(),
                        userService.findUsersByStatus(Status.UNVERIFIED).size(),
                        ((int) userService.findUsersByRoleAndStatus(Role.USER, Status.ACTIVE)
                                .stream()
                                .filter(x -> periodCalculatorService
                                        .countDaysOverDefaultPeriodOfCountersSendingForUser(x) > 0)
                                .count()));
            } else {
                userInfo = new ResponseWrapperUserInfo(
                        user.getName(),
                        user.getRole(),
                        user.getStatus(),
                        periodCalculatorService.countDaysOverDefaultPeriodOfCountersSendingForUser(user));
            }
            return new ResponseEntity<>(userInfo, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new StringMessage(USER_NOT_FOUND), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = GET_ADMIN_CONTACTS)
    public ResponseEntity<AdminContacts> getAdminContacts() {
        User admin = userService.findUserByRole(Role.ADMIN);
        return new ResponseEntity<>(
                new AdminContacts(admin.getName(), admin.getEmail(), admin.getPhone()), HttpStatus.OK);
    }
}
