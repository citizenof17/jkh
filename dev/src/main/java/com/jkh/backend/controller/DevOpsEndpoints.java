package com.jkh.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="DevOps Endpoints", description="Check if alive")
public class DevOpsEndpoints {
    @ApiOperation(value="live")
    @ResponseBody
    @RequestMapping(value = "/live",  method = RequestMethod.GET)
    public ResponseEntity<JSONObject> live() {
        JSONObject json = new JSONObject();
        json.put("hello", "world");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
