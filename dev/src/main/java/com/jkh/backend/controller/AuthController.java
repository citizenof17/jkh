package com.jkh.backend.controller;

import com.jkh.backend.model.User;
import com.jkh.backend.service.SecurityService;
import com.jkh.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.json.simple.JSONObject;
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
    public ResponseEntity<JSONObject> login(@RequestBody User user) {
        String login = user.getLogin();
        securityService.login(login, user.getPassword());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JSONObject json = new JSONObject();

        if (auth != null && auth.getName().equals(login)) {
            json.put("message", "User with login " + login + " logged in successfully");
            User userFromDB = userService.findUserByLogin(login);
            json.put("role", userFromDB.getRole());
            json.put("status", userFromDB.isActive());
            return ResponseEntity.ok(json);
        }

        json.put("message", "Login or password is incorrect");
        return new ResponseEntity<>(json, HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation(value="register")
    @ResponseBody
    @RequestMapping(value = "/register",  method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<JSONObject> register(@RequestBody User user) {
        User copyOfUser = SerializationUtils.clone(user);
        JSONObject json = userService.register(copyOfUser);
        if (json.get("isOk").equals(true)) {
            return login(user);
        } else {
            return new ResponseEntity<>(json, HttpStatus.CONFLICT);
        }
    }



}