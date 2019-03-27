package com.jkh.FE;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.steps.BaseSteps;
import com.jkh.FE.steps.HomePageSteps;
import com.jkh.FE.steps.LoginPageSteps;
import com.jkh.FE.steps.RegistrationPageSteps;
import com.jkh.utils.WaiterUtils;
import org.apache.commons.lang3.SerializationUtils;
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

    @Autowired
    private BaseSteps baseSteps;

    @BeforeClass(groups = {"FE", "Register"})
    public void prepareData() {
        dataLoadSteps.deleteAllData();
    }

    @BeforeMethod(groups = {"FE", "Register"})
    public void openRegisterPage() {
        //aseSteps.openPage(REGISTRATION_PAGE_ADDRESS);
        loginPageSteps.openLoginPage();
        loginPageSteps.clickRegistrationButton();
    }

    @Test(groups = {"FE", "Register"}, dataProvider = "correctRegisterData")
    @Title("Registration with all correct data about user")
    public void correctRegisterTest(RegisterRequest registerUser) {
        registrationPageSteps.fillRegistrationFields(registerUser);
        registrationPageSteps.clickRegistrationButton();
        WaiterUtils.wait(1);
        checkAddress(HOME_PAGE_ADDRESS);
        homePageSteps.checkUnverifiedInfo();
        dataLoadSteps.checkUser(registerUser);
    }

    @Test(groups = {"FE", "Register"}, dataProvider = "incorrectLoginData")
    @Title("Registration with incorrect user login")
    public void incorrectLoginRegisterTest(String incorrectLogin) {
        RegisterRequest incorrectUser = SerializationUtils.clone(CORRECT_REGISTER_USER);
        incorrectUser.setLogin(incorrectLogin);
        registrationPageSteps.fillRegistrationFields(incorrectUser);
        registrationPageSteps.checkLoginErrorMessage();
    }

    @Test(groups = {"FE", "Register"}, dataProvider = "incorrectNameData")
    @Title("Registration with incorrect user name")
    public void incorrectNameRegisterTest(String incorrectName) {
        RegisterRequest incorrectUser = SerializationUtils.clone(CORRECT_REGISTER_USER);
        incorrectUser.setName(incorrectName);
        registrationPageSteps.fillRegistrationFields(incorrectUser);
        registrationPageSteps.checkNameErrorMessage();
    }

    @Test(groups = {"FE", "Register"}, dataProvider = "incorrectFlatData")
    @Title("Registration with incorrect user flat number")
    public void incorrectFlatRegisterTest(Integer incorrectFlat) {
        RegisterRequest incorrectUser = SerializationUtils.clone(CORRECT_REGISTER_USER);
        incorrectUser.setFlatNumber(incorrectFlat);
        registrationPageSteps.fillRegistrationFields(incorrectUser);
        registrationPageSteps.checkFlatErrorMessage();
    }

    @Test(groups = {"FE", "Register"}, dataProvider = "incorrectEmailData")
    @Title("Registration with incorrect user email")
    public void incorrectEmailRegisterTest(String incorrectEmail) {
        RegisterRequest incorrectUser = SerializationUtils.clone(CORRECT_REGISTER_USER);
        incorrectUser.setEmail(incorrectEmail);
        registrationPageSteps.fillRegistrationFields(incorrectUser);
        registrationPageSteps.checkEmailErrorMessage();
    }

    @Test(groups = {"FE", "Register"}, dataProvider = "incorrectPhoneData")
    @Title("Registration with incorrect user phone number")
    public void incorrectPhoneRegisterTest(String incorrectPhone) {
        RegisterRequest incorrectUser = SerializationUtils.clone(CORRECT_REGISTER_USER);
        incorrectUser.setPhone(incorrectPhone);
        registrationPageSteps.fillRegistrationFields(incorrectUser);
        registrationPageSteps.checkPhoneErrorMessage();
    }

    @Test(groups = {"FE", "Register"}, dataProvider = "incorrectPasswordData")
    @Title("Registration with incorrect user password")
    public void incorrectPasswordRegisterTest(String incorrectPassword) {
        RegisterRequest incorrectUser = SerializationUtils.clone(CORRECT_REGISTER_USER);
        incorrectUser.setPassword(incorrectPassword);
        registrationPageSteps.fillRegistrationFields(incorrectUser);
        registrationPageSteps.checkPasswordErrorMessage();
    }

    @Test(groups = {"FE", "Register"})
    @Title("Registration with incorrect confirm user password")
    public void incorrectConfirmPasswordRegisterTest() {
        registrationPageSteps.fillRegistrationFields(CORRECT_REGISTER_USER, UNCONFIRMED_PASSWORD);
        registrationPageSteps.clickRegistrationButton();
        registrationPageSteps.checkConfirmPasswordErrorMessage();
    }

    @Test(groups = {"FE", "Register"})
    @Title("Registration with repeating fields: login, email, phone")
    public void incorrectRepeatingRegisterTest() {
        registrationPageSteps.fillRegistrationFields(CORRECT_REGISTER_USER);
        registrationPageSteps.clickRegistrationButton();
        WaiterUtils.wait(1);
        registrationPageSteps.checkRepeatLoginErrorMessage();
        registrationPageSteps.checkRepeatEmailErrorMessage();
        registrationPageSteps.checkRepeatPhoneErrorMessage();
    }

    @DataProvider(name = "correctRegisterData")
    public Object[] correctRegisterData() {
        ArrayList<RegisterRequest> registerRequests = new ArrayList<>();
        for (Object[] objects1 : correctRegisterData) {
            registerRequests.add((RegisterRequest) objects1[0]);
        }
        return registerRequests.toArray();
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[] incorrectLoginData() {
        return incorrectLoginData;
    }

    @DataProvider(name = "incorrectNameData")
    public Object[] incorrectNameData() {
        return incorrectNameData;
    }

    @DataProvider(name = "incorrectFlatData")
    public Object[] incorrectFlatData() {
        return incorrectFlatData;
    }

    @DataProvider(name = "incorrectEmailData")
    public Object[] incorrectEmailData() { return incorrectEmailData; }

    @DataProvider(name = "incorrectPhoneData")
    public Object[] incorrectPhoneData() {
        return incorrectPhoneData;
    }

    @DataProvider(name = "incorrectPasswordData")
    public Object[] incorrectPasswordData() {
        return incorrectPasswordData;
    }
}
