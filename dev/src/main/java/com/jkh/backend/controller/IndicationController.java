package com.jkh.backend.controller;

import com.jkh.backend.model.Indication;
import com.jkh.backend.model.User;
import com.jkh.backend.service.IndicationService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndicationController {

    @Autowired
    private IndicationService indicationService;

    @ResponseBody
    @RequestMapping(value = "/sendIndications",  method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<JSONObject> sendIndication(@RequestBody List<Indication> indications) {

        JSONObject json = indicationService.addIndications(indications);
        if (json.get("isOk").equals(true)) {
            return new ResponseEntity<>(json, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(json, HttpStatus.CONFLICT);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/lastNIndications")
    public ResponseEntity<JSONArray> lastNIndications() {
        JSONArray json = indicationService.getLastNIndications(50);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}

