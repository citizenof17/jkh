package com.jkh.FE;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.steps.HomePageSteps;
import com.jkh.FE.steps.LoginPageSteps;
import com.jkh.utils.WaiterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;

import static com.jkh.utils.Assertions.checkAddress;
import static com.jkh.utils.TestConstants.*;

@Features("FrontEnd")
@Stories("LoginPage UI tests")
@ContextConfiguration(classes = ConfigurationMain.class)
public class LoginTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Autowired
    private AuthSteps authSteps;

    @Autowired
    private LoginPageSteps loginPageSteps;

    @Autowired
    private HomePageSteps homePageSteps;

    @BeforeClass(groups = {"FE", "Login"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        //authSteps.registerUser((RegisterRequest) correctRegisterData[0][0]);
        for (Object[] objects: correctRegisterData) {
            authSteps.registerUser((RegisterRequest) objects[0]);
        }
    }

    @BeforeMethod(groups = {"FE", "Login"})
    public void openLoginPage() {
        loginPageSteps.openLoginPage();
    }

    @Test(groups = {"FE", "Login"}, dataProvider = "correctSignInData")
    @Title("Signing in with correct credential")
    public void correctSignIn(RegisterRequest correctUser) {
        //RegisterRequest correctUser = (RegisterRequest) correctRegisterData[0][0];
        //loginPageSteps.fillLoginInputField(correctUser.getLogin());
        //loginPageSteps.fillPasswordInputField(correctUser.getPassword());
        loginPageSteps.fillCorrectCredential(correctUser);
        //loginPageSteps.clickSignInButton();
        WaiterUtils.wait(1);
        checkAddress(HOME_PAGE_ADDRESS);
        homePageSteps.checkWelcomeTitle(correctUser.getName());
    }

    @Test(groups = {"FE", "Login"})
    @Title("Checking redirection on registration page")
    public void redirectOnRegistration() {
        loginPageSteps.clickRegistrationButton();
        checkAddress(REGISTRATION_PAGE_ADDRESS);
    }

    @DataProvider(name = "correctSignInData")
    public Object[] correctSignInData() {
        ArrayList<RegisterRequest> registerRequests = new ArrayList<>();
        for(Object[] objects1: correctRegisterData) {
            registerRequests.add((RegisterRequest) objects1[0]);
        }
        return registerRequests.subList(0, registerRequests.size()).toArray();
    }
}
