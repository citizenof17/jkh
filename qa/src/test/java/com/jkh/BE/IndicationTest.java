package com.jkh.BE;

import com.jkh.BE.models.IndicationRequest;
import com.jkh.BE.models.IndicationResponse;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.BE.steps.IndicationSteps;
import com.jkh.ConfigurationMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static com.jkh.utils.TestConstants.correctIndicationData;
import static com.jkh.utils.TestConstants.correctRegisterData;

@Features("BackEnd")
@Stories("Indication API tests")
@ContextConfiguration(classes = ConfigurationMain.class)
public class IndicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Autowired
    private AuthSteps authSteps;

    @Autowired
    private IndicationSteps indicationSteps;

    @BeforeClass(groups = {"BE", "Indication"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        authSteps.registerUser((RegisterRequest) correctRegisterData[0][0]);
    }

    @Test(groups = {"BE", "Indication"}, dataProvider = "correctIndicationData")
    @Title("Send correct indication data")
    public void sendCorrectIndication(List<IndicationRequest> indicationRequests, IndicationResponse indicationResponse) {
        indicationSteps.sendIndications(indicationRequests, authSteps.getSessionId());
        indicationSteps.checkCorrectIndications(indicationResponse);
    }

    @DataProvider(name = "correctIndicationData")
    public Object[] correctIndicationData() {
        return correctIndicationData;
    }
}
