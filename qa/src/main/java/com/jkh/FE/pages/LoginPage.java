package com.jkh.FE.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class LoginPage {

    private static final SelenideElement LOGIN_INPUT_FIELD = $(By.id("username"));
    private static final SelenideElement PASSWORD_INPUT_FIELD = $(By.id("password"));
    private static final SelenideElement SIGN_IN_BUTTON = $x("//input[contains(@type, 'submit')]");
    private static final SelenideElement REGISTRATION_LINK = $x("//a[contains(@ng-reflect-router-link, 'register')]");

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
}
