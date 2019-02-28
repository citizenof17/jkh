package com.jkh.backend.service.implementation;

import com.jkh.backend.model.*;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.repository.IndicationRepository;
import com.jkh.backend.service.CounterService;
import com.jkh.backend.service.IndicationService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.service.validation.IndicationServiceValidator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
            } else {
                for (Indication indication : indications) {
                    save(indication);
                }
            }
        }

        return json;
    }

    @Override
    public JSONArray getLastNIndications(Integer n) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);

        List<ResponseWrapperIndicationReport> indicationReportList;
        if (user.getRole().equals(Role.USER)) {
            indicationReportList = getIndications(user.getFlat());
        } else {
            indicationReportList = getIndications();
        }

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < Math.min(indicationReportList.size(), n); i++) {
            jsonArray.add(indicationReportList.get(i));
        }
        return jsonArray;
    }

    @Override
    public List<ResponseWrapperIndicationReport> getIndications() {
        return getIndications(indicationRepository.findIndicationsByOrderByDate());
    }

    @Override
    public List<ResponseWrapperIndicationReport> getIndications(Flat flat) {
        List<Indication> indicationList = new ArrayList<>();
        for (Counter counter : flat.getCounterSet()) {
            indicationList.addAll(indicationRepository.findIndicationsByCounterOrderByDate(counter));
        }
        return getIndications(indicationList);
    }

    @Override
    public List<ResponseWrapperIndicationReport> getIndications(Date left, Date right) {
        return getIndications(indicationRepository.findIndicationsByDateBetweenOrderByDate(left, right));
    }

    @Override
    public List<ResponseWrapperIndicationReport> getIndications(Flat flat, Date left, Date right) {
        List<Indication> indicationList = new ArrayList<>();
        for (Counter counter : flat.getCounterSet()) {
            indicationList.addAll(
                    indicationRepository.findIndicationsByCounterAndDateBetweenOrderByDate(counter, left, right));
        }
        return getIndications(indicationList);
    }

    private List<ResponseWrapperIndicationReport> getIndications(List<Indication> indicationList) {
        indicationList.sort(Comparator.comparing(Indication::getDate).reversed());

        if (indicationList.size() < 1) {
            return new ArrayList<>();
        }

        List<ResponseWrapperIndicationReport> result = new ArrayList<>();
        for (int i = 0; i < indicationList.size(); i++) {
            Indication cur = indicationList.get(i);
            Indication prev = i == 0 ? null : indicationList.get(i - 1);
            if (prev == null || !cur.getDate().equals(prev.getDate()) ||
                                !cur.getCounter().getFlat().equals(prev.getCounter().getFlat())) {
                result.add(new ResponseWrapperIndicationReport(cur));
            } else {
                result.get(result.size() - 1).addIndication(cur);
            }
        }
        return result;
    }
}