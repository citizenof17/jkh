package com.jkh.FE;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.models.RegisterUser;
import com.jkh.FE.steps.HomePageSteps;
import com.jkh.FE.steps.LoginPageSteps;
import com.jkh.FE.steps.RegistrationPageSteps;
import com.jkh.utils.WaiterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jkh.utils.Assertions.checkAddress;
import static com.jkh.utils.TestConstants.*;

@Features("FrontEnd")
@Stories("RegistrationPage UI tests")
@ContextConfiguration(classes = ConfigurationMain.class)
public class RegistrationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Autowired
    private LoginPageSteps loginPageSteps;

    @Autowired
    private RegistrationPageSteps registrationPageSteps;

    @Autowired
    private HomePageSteps homePageSteps;

    @BeforeClass(groups = {"FE", "Register"})
    public void prepareData() {
        dataLoadSteps.deleteAllData();
    }

    @BeforeMethod(groups = {"FE", "Register"})
    public void openRegisterPage() {
        loginPageSteps.openLoginPage();
        loginPageSteps.clickRegistrationButton();
    }

    @Test(groups = {"FE", "Register"})
    @Title("Registration with all correct data about user")
    public void correctRegisterTest() {
        RegisterRequest registerRequest = (RegisterRequest) correctRegisterData[0][0];
        RegisterUser registerUser = new RegisterUser(registerRequest, registerRequest.getPassword());
        registrationPageSteps.fillRegistrationFields(registerUser);
        registrationPageSteps.clickRegistrationButton();
        WaiterUtils.wait(1);
        checkAddress(HOME_PAGE_ADDRESS);
        homePageSteps.checkWelcomeTitle(registerUser.getName());
    }
}
