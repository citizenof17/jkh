package com.jkh.backend.service.implementation;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.enums.CounterType;
import com.jkh.backend.repository.CounterRepository;
import com.jkh.backend.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterRepository counterRepository;

    @Override
    public void save(Counter counter) {
        counterRepository.save(counter);
    }

    @Override
    public void addThreeCounters(Flat flat) {
        CounterType[] types = {CounterType.ELECTRICITY, CounterType.HOT_WATER, CounterType.COLD_WATER};
        for (CounterType type : types) {
            Counter counter = new Counter(type, flat);
            save(counter);
        }
    }

    @Override
    public Counter findCounterByFlatAndType(Flat flat, CounterType counterType) {
        return counterRepository.findCounterByFlatAndType(flat, counterType);
    }

    @Override
    public Counter findCounterByFlatAndNumber(Flat flat, String number) {
        return counterRepository.findCounterByFlatAndNumber(flat, number);
    }

    @Override
    public Counter findCounterByFlatAndIndication(Flat flat, Indication indication) {
        Counter counter = indication.getCounter();
        CounterType counterType = counter.getType();
        if (counterType != null) {
            return findCounterByFlatAndType(flat, counterType);
        } else {
            return findCounterByFlatAndNumber(flat, counter.getNumber());
        }
    }
}
