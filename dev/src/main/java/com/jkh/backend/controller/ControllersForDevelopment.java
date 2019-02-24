package com.jkh.backend.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllersForDevelopment {

    @ResponseBody
    @RequestMapping(value = "/dev",  method = RequestMethod.GET)
    public ResponseEntity<JSONObject> dev() {
        JSONObject json = new JSONObject();
        json.put("dev", "dev");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/qwe",  method = RequestMethod.POST)
    public ResponseEntity<JSONObject> qwe() {
        JSONObject json = new JSONObject();
        json.put("qwe", "qwe");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
