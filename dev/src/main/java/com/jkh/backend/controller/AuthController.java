package com.jkh.backend.controller;

import com.jkh.backend.model.User;
import com.jkh.backend.model.wrappers.ResponseWrapperRegistrationValidator;
import com.jkh.backend.model.wrappers.ResponseWrapperUserAuth;
import com.jkh.backend.service.SecurityService;
import com.jkh.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Api(value="Login System", description="Login via login and password")
public class AuthController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @ApiOperation(value="login")
    @ResponseBody
    @RequestMapping(value = "/login",  method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Object> login(@RequestBody User user) {
        String login = user.getLogin();
        securityService.login(login, user.getPassword());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getName().equals(login)) {
            User userFromDB = userService.findUserByLogin(login);
            return ResponseEntity.ok(
                    new ResponseWrapperUserAuth(
                            userFromDB.getName(),
                            userFromDB.getRole(),
                            userFromDB.isActive()));
        }
        return new ResponseEntity<>(
                new ResponseWrapperUserAuth("Login or password is incorrect"), HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation(value="register")
    @ResponseBody
    @RequestMapping(value = "/register",  method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Object> register(@RequestBody User user) {
        User copyOfUser = SerializationUtils.clone(user);
        ResponseWrapperRegistrationValidator json = userService.register(copyOfUser);
        if (json.isOk()) {
            return login(user);
        } else {
            return new ResponseEntity<>(json, HttpStatus.CONFLICT);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/userInfo")
    public ResponseEntity<ResponseWrapperUserAuth> getUserInfo() {
        try {
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findUserByLogin(login);
            return ResponseEntity.ok(
                    new ResponseWrapperUserAuth(
                            user.getName(),
                            user.getRole(),
                            user.isActive()));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseWrapperUserAuth("User not found"), HttpStatus.UNAUTHORIZED);
        }
    }



}