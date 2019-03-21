package com.jkh.BE;

import com.jkh.BE.models.RegisterBadResponse;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.models.RegisterResponse;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jkh.utils.TestConstants.correctRegisterData;
import static com.jkh.utils.TestConstants.incorrectRegisterData;

@ContextConfiguration(classes = ConfigurationMain.class)
public class RegisterTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AuthSteps authSteps;

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @BeforeClass(groups = {"BE", "Register"})
    public void prepareData() {
        dataLoadSteps.deleteAllData();
    }

    @Test(groups = {"BE", "Register"}, dataProvider = "CorrectRegister")
    @Title("Correct tests for registration")
    public void registerTest(RegisterRequest request, RegisterResponse response) throws Exception {
        authSteps.registerUser(request);
        authSteps.checkRegisterResponse(response);
    }

    @DataProvider(name = "CorrectRegister")
    public Object[] getRegisterData() {
        return correctRegisterData;
    }

    @Test(groups = {"BE", "Register"}, dataProvider = "IncorrectRegister", dependsOnMethods = {"registerTest"})
    @Title("Incorrect tests for registration")
    public void registerIncorrectTest(RegisterRequest request, RegisterBadResponse response) throws Exception {
        authSteps.registerIncorrectUser(request);
        authSteps.checkIncorrectRegisterResponse(response);
    }

    @DataProvider(name = "IncorrectRegister")
    public Object[] getIncorrectRegisterData() {
        return incorrectRegisterData;
    }
}
