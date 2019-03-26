package com.jkh.FE.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class LoginPage {

    private static final String LOGIN_ERROR_TEXT = "Некорректный формат имени пользователя.";
    private static final String PASSWORD_ERROR_TEXT = "Некорректный формат пароля.";
    private static final String CREDENTIAL_ERROR_TEXT = "Неверный логин или пароль";


    private static final SelenideElement LOGIN_INPUT_FIELD = $(By.id("username"));
    private static final SelenideElement PASSWORD_INPUT_FIELD = $(By.id("password"));
    private static final SelenideElement SIGN_IN_BUTTON = $x("//input[contains(@type, 'submit')]");
    private static final SelenideElement REGISTRATION_LINK = $x("//a[contains(@ng-reflect-router-link, 'register')]");
    private static final SelenideElement LOGIN_ERROR_MESSAGE = findErrorMessage(LOGIN_ERROR_TEXT);
    private static final SelenideElement PASSWORD_ERROR_MESSAGE = findErrorMessage(PASSWORD_ERROR_TEXT);
    private static final SelenideElement CREDENTIAL_ERROR_MESSAGE = findErrorMessage(CREDENTIAL_ERROR_TEXT);

    private static SelenideElement findErrorMessage(String message) {
        return $x(String.format("//div[contains(text(), '%s')]", message));
    }

    public void fillLogin(String login) {
        LOGIN_INPUT_FIELD.setValue(login);
    }

    public void fillPassword(String password) {
        PASSWORD_INPUT_FIELD.setValue(password);
    }

    public void clickSignInButton() {
        SIGN_IN_BUTTON.click();
    }

    public void clickRegistrationButton() {
        REGISTRATION_LINK.click();
    }

    public void reset() {
        LOGIN_INPUT_FIELD.click();
    }

    public Boolean loginErrorMessageVisibility() {
        return LOGIN_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean passwordErrorMessageVisibility() {
        return PASSWORD_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean credentialErrorMessageVisibility() {
        return CREDENTIAL_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean signInButtonEnable() {
        return SIGN_IN_BUTTON.isEnabled();
    }
}
