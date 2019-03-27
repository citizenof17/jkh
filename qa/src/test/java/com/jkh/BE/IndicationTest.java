package com.jkh.BE;

import com.jkh.BE.models.IndicationRequest;
import com.jkh.BE.models.IndicationResponse;
import com.jkh.BE.models.LoginRequest;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.models.enums.Status;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.BE.steps.IndicationSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.pages.EditInhabitantsPage;
import com.jkh.FE.steps.EditInhabitantsPageSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static com.jkh.utils.TestConstants.*;

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

    @Autowired
    private EditInhabitantsPageSteps editInhabitantsPageSteps;

    @BeforeClass(groups = {"BE", "Indication"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        authSteps.registerUser(CORRECT_REGISTER_USER);
        dataLoadSteps.ubdateAllUsersToActive();
    }

    @Test(groups = {"BE", "Indication"}, dataProvider = "correctIndicationData")
    @Title("Send correct indication data")
    public void sendCorrectIndication(List<IndicationRequest> indicationRequests, IndicationResponse indicationResponse) throws IOException {
        indicationSteps.sendIndications(indicationRequests, authSteps.getSessionId());
        indicationSteps.checkCorrectIndications(indicationResponse);
    }

    @DataProvider(name = "correctIndicationData")
    public Object[] correctIndicationData() {
        return correctIndicationData;
    }
}
