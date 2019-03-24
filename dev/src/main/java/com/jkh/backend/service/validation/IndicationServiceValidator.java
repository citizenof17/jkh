package com.jkh.backend.service.validation;

import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.enums.CounterType;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jkh.backend.service.validation.ValidationMessages.*;

@Service
public class IndicationServiceValidator {

    public static String checkIndication(Indication newIndication, Indication prevIndication) {

        //todo compare interval between newIndication date and prevIndication date (?)

        if (prevIndication != null && (prevIndication.getValue() > newIndication.getValue()
                || prevIndication.getDate().isAfter(newIndication.getDate()))) {
            return INDICATION_INCORRECT;
        }

        return OK;
    }

    public static ResponseWrapperStateWithMessages checkListOfIndications(List<Indication> indications) {

        MutableBoolean isOk = new MutableBoolean(true);
        String message = OK;

        if (indications.size() != 3) {
            isOk.setFalse();
            message = INDICATIONS_LENGTH;
        } else {
            int hotWater = 0;
            int coldWater = 0;
            int electricity = 0;

            for (Indication indication : indications) {
                if (indication == null || indication.getCounter() == null
                        || indication.getCounter().getType() == null
                        || indication.getValue() == null) {
                    isOk.setFalse();
                    message = INDICATION_INCORRECT;
                    break;
                }

                CounterType type = indication.getCounter().getType();

                if (type.equals(CounterType.COLD_WATER)) {
                    coldWater++;
                } else if (type.equals(CounterType.HOT_WATER)) {
                    hotWater++;
                } else if (type.equals(CounterType.ELECTRICITY)) {
                    electricity++;
                }

            }

            if (hotWater > 1 || coldWater > 1 || electricity > 1) {
                isOk.setFalse();
                message = INDICATION_DUPLICATE;
            }
        }

        return new ResponseWrapperStateWithMessages(isOk.booleanValue(), new ArrayList<>(Arrays.asList(message)));
    }
}
