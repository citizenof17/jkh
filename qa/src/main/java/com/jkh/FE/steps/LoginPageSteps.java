package com.jkh.FE.steps;

import com.jkh.FE.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import static com.jkh.utils.Assertions.checkAddress;
import static com.jkh.utils.TestConstants.LOGIN_PAGE_ADDRESS;
import static com.jkh.utils.TestConstants.REGISTRATION_PAGE_ADDRESS;

@Component
public class LoginPageSteps {

    private final LoginPage loginPage;
    private final BaseSteps baseSteps;

    @Autowired
    public LoginPageSteps(LoginPage loginPage, BaseSteps baseSteps) {
        this.loginPage = loginPage;
        this.baseSteps = baseSteps;
    }

    @Step("Opening Login page")
    public void openLoginPage() {
        baseSteps.openPage(LOGIN_PAGE_ADDRESS);
        checkAddress(LOGIN_PAGE_ADDRESS);
    }

    @Step("Filling login input field on: {0}")
    public void fillLoginInputField(String login) {
        loginPage.fillLogin(login);
    }

    @Step("Filling password input field on: {0}")
    public void fillPasswordInputField(String password) {
        loginPage.fillPassword(password);
    }

    @Step("Clicking on button SignIn")
    public void clickSignInButton() {
        loginPage.clickSignInButton();
    }

    @Step("Redirection on registration page")
    public void clickRegistrationButton() {
        loginPage.clickRegistrationButton();
        checkAddress(REGISTRATION_PAGE_ADDRESS);
    }
}
