package com.jkh.BE.steps;

import com.jkh.BE.clients.IndicationClient;
import com.jkh.BE.models.IndicationRequest;
import com.jkh.BE.models.IndicationResponse;
import com.jkh.utils.Assertions;
import com.jkh.utils.ObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.List;

@Component
public class IndicationSteps {

    @Autowired
    private IndicationClient indicationClient;

    private IndicationResponse indicationCorrectResponse;

    @Step("Sending correct indications data")
    public void sendIndications(List<IndicationRequest> indicationRequests, String sessionId) throws IOException {
        indicationCorrectResponse = ObjMapper.body2Object(indicationClient.sendIndications(indicationRequests, sessionId), IndicationResponse.class);
        System.out.println(indicationCorrectResponse.toString());
    }

    @Step("Checking correct indication response")
    public void checkCorrectIndications(IndicationResponse expectedResponse) {
        Assertions.compareIfEqual(indicationCorrectResponse, expectedResponse);
    }
}
