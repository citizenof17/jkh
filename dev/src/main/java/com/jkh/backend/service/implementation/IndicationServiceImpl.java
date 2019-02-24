package com.jkh.backend.service.implementation;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.User;
import com.jkh.backend.repository.IndicationRepository;
import com.jkh.backend.service.CounterService;
import com.jkh.backend.service.IndicationService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.service.validation.IndicationServiceValidator;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IndicationServiceImpl implements IndicationService {
    @Autowired
    private IndicationRepository indicationRepository;

    @Autowired
    private CounterService counterService;

    @Autowired
    private UserService userService;

    @Override
    public void save(Indication indication) {
        indicationRepository.save(indication);
    }


    @Override
    public Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter) {
        return indicationRepository.findDistinctTopIndicationByCounterOrderByDateDesc(counter);
    }

    @Override
    public JSONObject addIndication(Flat flat, Indication indication) {
        Counter counter = counterService.findCounterByFlatAndIndication(flat, indication);

        indication.setCounter(counter);

        JSONObject json = IndicationServiceValidator.checkIndication(indication,
                findDistinctTopIndicationByCounterOrderByDateDesc(counter));

        if (json.get("isOk").equals(true)) {
            save(indication);
        }

        return json;
    }

    @Override
    public JSONObject addIndications(List<Indication> indications) {
        JSONObject json = IndicationServiceValidator.checkListOfIndications(indications);
        if (json.get("isOk").equals(true)) {
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findUserByLogin(login);

            Flat flat = user.getFlat();
            List<JSONObject> messagesPerIndication = new ArrayList<>();

            Date curDate = new Date();
            for (Indication indication : indications) {
                indication.setDate(curDate);
                JSONObject oneIndicationJson = addIndication(flat, indication);

                if (oneIndicationJson.get("isOk").equals(false)) {
                    json.replace("isOk", false);
                }

                messagesPerIndication.add(oneIndicationJson);
            }

            if (json.get("isOk").equals(false)) {
                json.replace("message", messagesPerIndication);
            }
        }


        return json;
    }



}