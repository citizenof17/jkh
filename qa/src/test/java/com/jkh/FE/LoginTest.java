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
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jkh.utils.Assertions.checkAddress;
import static com.jkh.utils.TestConstants.USER_PAGE_ADDRESS;
import static com.jkh.utils.TestConstants.correctRegisterData;

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
        authSteps.registerUser((RegisterRequest) correctRegisterData[0][0]);
    }

    @BeforeMethod(groups = {"FE", "Login"})
    public void openLoginPage() {
        loginPageSteps.openLoginPage();
    }

    @Test(groups = {"FE", "Login"})
    @Title("Signing in with correct credential")
    public void correctSignIn() {
        RegisterRequest correctUser = (RegisterRequest) correctRegisterData[0][0];
        loginPageSteps.fillLoginInputField(correctUser.getLogin());
        loginPageSteps.fillPasswordInputField(correctUser.getPassword());
        loginPageSteps.clickSignInButton();
        WaiterUtils.wait(1);
        checkAddress(USER_PAGE_ADDRESS);
        homePageSteps.checkWelcomeTitle(correctUser.getName());
    }
}
