package com.jkh.backend.service.implementation;

import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReportRow;
import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import com.jkh.backend.repository.IndicationRepository;
import com.jkh.backend.service.CounterService;
import com.jkh.backend.service.IndicationService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.service.validation.IndicationServiceValidator;
import com.jkh.backend.service.validation.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    public String addIndication(Flat flat, Indication indication) {
        Counter counter = counterService.findCounterByFlatAndIndication(flat, indication);

        indication.setCounter(counter);

        return IndicationServiceValidator.checkIndication(indication,
                findDistinctTopIndicationByCounterOrderByDateDesc(counter));
    }

    @Override
    public ResponseWrapperStateWithMessages addIndications(List<Indication> indications) {
        ResponseWrapperStateWithMessages listOfIndications = IndicationServiceValidator.checkListOfIndications(indications);
        if (listOfIndications.getIsOk()) {
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findUserByLogin(login);

            Flat flat = user.getFlat();
            List<String> messagesPerIndication = new ArrayList<>();

            LocalDateTime curDate = LocalDateTime.now();
            for (Indication indication : indications) {
                indication.setDate(curDate);
                indication.setUser(user);
                String indicationStatus = addIndication(flat, indication);

                if (!indicationStatus.equals(ValidationMessages.OK)) {
                    listOfIndications.setIsOk(false);
                }

                messagesPerIndication.add(indicationStatus);
            }

            listOfIndications.setMessages(messagesPerIndication);
        }


        if (listOfIndications.getIsOk()) {
            for (Indication indication : indications) {
                save(indication);
            }
        }

        return listOfIndications;
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getLastNIndications(User user, Integer n) {
        List<ResponseWrapperIndicationReportRow> indicationReportList;
        if (user.getRole().equals(Role.USER)) {
            indicationReportList = getIndications(user.getFlat());
        } else {
            indicationReportList = getIndications();
        }

        return indicationReportList.subList(0, Math.min(indicationReportList.size(), n));
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications() {
        return getIndications(indicationRepository.findIndicationsByOrderByDate());
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications(Flat flat) {
        if (flat == null) {
            return getIndications();
        }
        List<Indication> indicationList = new ArrayList<>();
        for (Counter counter : flat.getCounterSet()) {
            indicationList.addAll(indicationRepository.findIndicationsByCounterOrderByDate(counter));
        }
        return getIndications(indicationList);
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications(Status status) {
        if (status == null) {
            return getIndications();
        }

        return getIndications(indicationRepository.findIndicationsByUserStatusOrderByDate(status));
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications(LocalDateTime left, LocalDateTime right) {
        if (left == null || right == null) {
            return getIndications();
        }
        return getIndications(indicationRepository.findIndicationsByDateBetweenOrderByDate(left, right));
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications(Flat flat, LocalDateTime left, LocalDateTime right) {
        if (flat == null) {
            return getIndications(left, right);
        }
        if (left == null || right == null) {
            return getIndications(flat);
        }
        List<Indication> indicationList = new ArrayList<>();
        for (Counter counter : flat.getCounterSet()) {
            indicationList.addAll(
                    indicationRepository.findIndicationsByCounterAndDateBetweenOrderByDate(counter, left, right));
        }
        return getIndications(indicationList);
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications(Status status, Flat flat) {
        if (status == null) {
            return getIndications(flat);
        }
        if (flat == null) {
            return getIndications(status);
        }

        List<Indication> indicationList = new ArrayList<>();
        for (Counter counter : flat.getCounterSet()) {
            indicationList.addAll(indicationRepository.findIndicationsByUserStatusAndCounterOrderByDate(status, counter));
        }
        return getIndications(indicationList);
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications(
            Status status, LocalDateTime left, LocalDateTime right) {

        if (status == null) {
            return getIndications(left, right);
        }
        if (left == null || right == null) {
            return getIndications(status);
        }

        return getIndications(
                indicationRepository.findIndicationsByUserStatusAndDateBetweenOrderByDate(status, left, right));
    }

    @Override
    public List<ResponseWrapperIndicationReportRow> getIndications(
            Status status, Flat flat, LocalDateTime left, LocalDateTime right) {

        if (status == null) {
            return getIndications(flat, left, right);
        }
        if (flat == null) {
            return getIndications(status, left, right);
        }
        if (left == null || right == null) {
            return getIndications(status, flat);
        }

        List<Indication> indicationList = new ArrayList<>();
        for (Counter counter : flat.getCounterSet()) {
            indicationList.addAll(
                    indicationRepository.findIndicationsByUserStatusAndCounterAndDateBetweenOrderByDate(
                            status, counter, left, right));
        }
        return getIndications(indicationList);
    }


    private List<ResponseWrapperIndicationReportRow> getIndications(List<Indication> indicationList) {
        indicationList.sort(Comparator.comparing(Indication::getDate).reversed());

        if (indicationList.isEmpty()) {
            return new ArrayList<>();
        }

        List<ResponseWrapperIndicationReportRow> result = new ArrayList<>();
        for (int i = 0; i < indicationList.size(); i++) {
            Indication cur = indicationList.get(i);
            Indication prev = i == 0 ? null : indicationList.get(i - 1);
            if (prev == null || !cur.getDate().equals(prev.getDate()) ||
                                !cur.getCounter().getFlat().equals(prev.getCounter().getFlat())) {
                result.add(new ResponseWrapperIndicationReportRow(cur));
            } else {
                result.get(result.size() - 1).addIndication(cur);
            }
        }
        return result;
    }

}