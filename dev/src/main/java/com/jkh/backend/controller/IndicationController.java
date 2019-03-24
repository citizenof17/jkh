package com.jkh.backend.controller;

import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.model.Indication;
import com.jkh.backend.service.IndicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndicationController {

    private static final String SEND_INDICATIONS_ENDPOINT = "/sendIndications";

    @Autowired
    private IndicationService indicationService;

    @ResponseBody
    @RequestMapping(value = SEND_INDICATIONS_ENDPOINT, method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Object> sendIndication(@RequestBody List<Indication> indications) {

        ResponseWrapperStateWithMessages listOfIndications = indicationService.addIndications(indications);
        if (listOfIndications.getIsOk()) {
            return new ResponseEntity<>(listOfIndications, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(listOfIndications, HttpStatus.CONFLICT);
        }

    }

}

