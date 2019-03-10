package com.jkh.backend.service.validation;

import com.jkh.backend.model.Indication;
import com.jkh.backend.model.enums.CounterType;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jkh.backend.service.validation.ValidationMessages.*;

@Service
public class IndicationServiceValidator {



    public static JSONObject checkIndication(Indication newIndication,
                                             Indication prevIndication) {
        JSONObject json = new JSONObject();
        MutableBoolean isOk = new MutableBoolean(true);

        //todo compare interval between newIndication date and prevIndication date (?)

        if (prevIndication != null && (prevIndication.getValue() > newIndication.getValue()
                || prevIndication.getDate().isAfter(newIndication.getDate()))) {
            isOk.setFalse();
            json.put("message", INDICATION_INCORRECT);
        }

        json.put("isOk", isOk.getValue());
        return json;
    }


    public static JSONObject checkListOfIndications(List<Indication> indications) {
        JSONObject json = new JSONObject();
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

        json.put("isOk", isOk.getValue());
        json.put("message", message);
        return json;
    }


}
