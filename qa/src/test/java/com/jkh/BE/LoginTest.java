package com.jkh.BE;

import com.jkh.BE.models.LoginRequest;
import com.jkh.BE.models.LoginResponse;
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
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

import static com.jkh.utils.TestConstants.correctRegisterData;

@Features("Login API tests")
@ContextConfiguration(classes = ConfigurationMain.class)
public class LoginTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Autowired
    private AuthSteps authSteps;

    @BeforeClass(groups = {"BE", "Login"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        for (Object[] registerRequest: correctRegisterData) {
            authSteps.registerUser((RegisterRequest) registerRequest[0]);
        }
    }

    @Test(groups = {"BE", "Login"}, dataProvider = "CorrectRegister")
    public void loginCorrectUser(RegisterRequest request, RegisterResponse response) throws IOException {
        LoginRequest loginRequest = new LoginRequest(request);
        authSteps.loginCorrectUser(loginRequest);
        authSteps.checkLoginResponse(new LoginResponse(response));
    }

    @DataProvider(name = "CorrectRegister")
    public Object[] getRegisterData() {
        return correctRegisterData;
    }
}
