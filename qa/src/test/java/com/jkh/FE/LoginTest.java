package com.jkh.FE;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.steps.AdminPageSteps;
import com.jkh.FE.steps.HomePageSteps;
import com.jkh.FE.steps.LoginPageSteps;
import com.jkh.utils.WaiterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private AdminPageSteps adminPageSteps;

    @Autowired
    private ConfigurationFE configurationFE;

    @Value("${ADMIN_LOGIN:Administrator}")
    private String login;

    @Value("${ADMIN_PASSWORD:Administrator_1}")
    private String password;

    @Value("${ADMIN_NAME:Администратор Великий Ужаснович}")
    private String name;

    @BeforeClass(groups = {"FE", "Login"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        //authSteps.registerUser((RegisterRequest) correctRegisterData[0][0]);
        for (Object[] objects : correctRegisterData) {
            authSteps.registerUser((RegisterRequest) objects[0]);
        }
    }

    @BeforeMethod(groups = {"FE", "Login"})
    public void openLoginPage() {
        loginPageSteps.openLoginPage();
    }

    @Test(groups = {"FE", "Login"})
    @Title("Signing in with correct ADMIN credential")
    public void correctAdminSignIn() {
        loginPageSteps.fillCorrectCredential(login, password);
        WaiterUtils.wait(1);
        checkAddress(ADMIN_PAGE_ADDRESS);
        adminPageSteps.checkWelcomeTitle(name);
    }

    @Test(groups = {"FE", "Login"}, dataProvider = "correctSignInData")
    @Title("Signing in with correct USER credential")
    public void correctSignIn(RegisterRequest correctUser) {
        loginPageSteps.fillCorrectCredential(correctUser.getLogin(), correctUser.getPassword());
        WaiterUtils.wait(1);
        checkAddress(HOME_PAGE_ADDRESS);
        homePageSteps.checkUnverifiedInfo();
    }

    @Test(groups = {"FE", "Login"})
    @Title("Checking redirection on registration page")
    public void redirectOnRegistration() {
        loginPageSteps.clickRegistrationButton();
        checkAddress(REGISTRATION_PAGE_ADDRESS);
    }

    @Test(groups = {"FE", "Login"}, dataProvider = "incorrectLoginData")
    @Title("Signing in with incorrect format login")
    public void invalidLoginSignIn(String incorrectLogin) {
        loginPageSteps.fillIncorrectCredential(incorrectLogin, CORRECT_FORMAT_PASSWORD);
        loginPageSteps.checkLoginErrorMessage();
    }

    @Test(groups = {"FE", "Login"}, dataProvider = "incorrectPasswordData", enabled = false)
    @Title("Signing in with incorrect format password")
    public void invalidPasswordSignIn(String incorrectPassword) {
        loginPageSteps.fillIncorrectCredential(CORRECT_FORMAT_LOGIN, incorrectPassword);
        loginPageSteps.clickSignInButton();
        loginPageSteps.reset();
        loginPageSteps.checkPasswordErrorMessage();
    }

    @Test(groups = {"FE", "Login"})
    @Title("Signing in with incorrect format login and password")
    public void invalidCredentialFormatSignIn() {
        loginPageSteps.fillIncorrectCredential((String) incorrectLoginData[0], (String) incorrectPasswordData[0]);
        loginPageSteps.reset();
        loginPageSteps.checkLoginErrorMessage();
    }

    @Test(groups = {"FE", "Login"})
    @Title("Signing in with incorrect credential")
    public void invalidCredentialSignIn() {
        loginPageSteps.fillCorrectCredential(CORRECT_FORMAT_LOGIN, CORRECT_FORMAT_PASSWORD);
        loginPageSteps.checkCredentialErrorMessage();
    }

    @DataProvider(name = "correctSignInData")
    public Object[] correctSignInData() {
        ArrayList<RegisterRequest> registerRequests = new ArrayList<>();
        for (Object[] objects1 : correctRegisterData) {
            registerRequests.add((RegisterRequest) objects1[0]);
        }
        return registerRequests.subList(0, registerRequests.size()).toArray();
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[] incorrectLoginData() {
        return incorrectLoginData;
    }

    @DataProvider(name = "incorrectPasswordData")
    public Object[] incorrectPasswordData() {
        return incorrectPasswordData;
    }
}
