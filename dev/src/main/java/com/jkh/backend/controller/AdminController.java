package com.jkh.backend.controller;

import com.jkh.backend.dto.StringMessage;
import com.jkh.backend.dto.FullUserInfo;
import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.model.Common;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import com.jkh.backend.service.CommonService;
import com.jkh.backend.service.FlatService;
import com.jkh.backend.service.PeriodCalculatorService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.utils.CommonKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private static final String SET_NOTIFICATIONS_PERIOD_ENDPOINT = "/setNotificationsPeriod";
    private static final String GET_WHO_DID_NOT_SEND_ENDPOINT = "/getWhoDidNotSend";
    private static final String GET_NEWCOMERS_ENDPOINT = "/getNewcomers";
    private static final String SET_NEWCOMERS_ENDPOINT = "/setNewcomers";
    private static final String GET_FLAT_INHABITANTS_ENDPOINT = "/getFlatInhabitants";
    private static final String SET_FLAT_INHABITANTS_ENDPOINT = "/setFlatInhabitants";

    private static final String UNAVAILABLE_ACTION = "This action is unavailable";
    private static final String FLAT_NOT_FOUND = "Flat was not found";

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private FlatService flatService;

    @Autowired
    private PeriodCalculatorService periodCalculatorService;

    @ResponseBody
    @RequestMapping(value = SET_NOTIFICATIONS_PERIOD_ENDPOINT, method = RequestMethod.POST,
            consumes = {"application/json"})
    public ResponseEntity<Object> setNotificationsPeriod(@RequestParam Integer notificationsPeriod) {
        try {
            Common common = commonService.findCommonByKey(CommonKeys.DEFAULT_PERIOD_BETWEEN_COUNTERS_SENDINGS);
            common.setValue(notificationsPeriod.toString());
            commonService.save(common);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new StringMessage(UNAVAILABLE_ACTION), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = GET_WHO_DID_NOT_SEND_ENDPOINT)
    public ResponseEntity<Object> getWhoDidNotSend() {
        try {
            List<FullUserInfo> whoDidNotSend = userService.getAllUsers().stream()
                    .filter(x -> x.getRole().equals(Role.USER))
                    .filter(x -> x.getStatus().equals(Status.ACTIVE))
                    .map(x -> new FullUserInfo(x.getName(), x.getFlat().getNumber(),
                            x.getPhone(), x.getLogin(), x.getEmail(), x.getStatus(),
                            periodCalculatorService.countDaysOverDefaultPeriodOfCountersSendingForUser(x)))
                    .filter(x -> x.getDaysOverDefaultPeriodOfCountersSending() > 0)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(whoDidNotSend, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new StringMessage(UNAVAILABLE_ACTION), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = GET_NEWCOMERS_ENDPOINT)
    public ResponseEntity<Object> getNewcomers() {
        try {
            List<List<FullUserInfo>> newcomersInBlocks = userService.getAllUsers().stream()
                    .filter(x -> x.getRole().equals(Role.USER))
                    .filter(x -> x.getStatus().equals(Status.UNVERIFIED))
                    .map(x -> new ArrayList<>(x.getFlat().getUserSet()).stream()
                            .map(y -> new FullUserInfo(y.getName(), y.getFlat().getNumber(),
                                    y.getPhone(), y.getLogin(), y.getEmail(), y.getStatus(), null))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(newcomersInBlocks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new StringMessage(UNAVAILABLE_ACTION), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = SET_NEWCOMERS_ENDPOINT, method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Object> setNewcomersEndpoint(
            @RequestBody List<List<FullUserInfo>> newcomersInBlocks) {
        ResponseWrapperStateWithMessages ans = userService.setStatusChangesBulk(newcomersInBlocks);
        return new ResponseEntity<>(ans, ans.getIsOk() ? HttpStatus.OK : HttpStatus.CONFLICT);
    }


    @ResponseBody
    @RequestMapping(value = GET_FLAT_INHABITANTS_ENDPOINT)
    public ResponseEntity<Object> getFlatInhabitants(@RequestParam Integer flatNumber) {
        try {
            Flat flat = flatService.findFlatByNumber(flatNumber);
            if (flat == null) {
                return new ResponseEntity<>(new StringMessage(FLAT_NOT_FOUND), HttpStatus.CONFLICT);
            }

            List<FullUserInfo> flatInhabitants = flat.getUserSet().stream()
                    .map(x -> new FullUserInfo(x.getName(), x.getFlat().getNumber(),
                            x.getPhone(), x.getLogin(), x.getEmail(), x.getStatus(), null))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(flatInhabitants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new StringMessage(UNAVAILABLE_ACTION), HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseBody
    @RequestMapping(value = SET_FLAT_INHABITANTS_ENDPOINT)
    public ResponseEntity<Object> setFlatInhabitants(@RequestBody List<FullUserInfo> flatInhabitants) {
        List<List<FullUserInfo>> lst = new ArrayList<>();
        lst.add(flatInhabitants);
        ResponseWrapperStateWithMessages ans = userService.setStatusChangesBulk(lst);
        return new ResponseEntity<>(ans, ans.getIsOk() ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

}
