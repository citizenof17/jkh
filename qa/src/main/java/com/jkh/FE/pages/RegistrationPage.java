package com.jkh.FE.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class RegistrationPage {

    private static final SelenideElement LOGIN_INPUT_FIELD = $(By.id("username"));
    private static final SelenideElement FULL_NAME_INPUT_FIELD = $(By.id("fullname"));
    private static final SelenideElement FLAT_NUMBER_INPUT_FIELD = $(By.id("flatnumber"));
    private static final SelenideElement EMAIL_INPUT_FIELD = $(By.id("email_address"));
    private static final SelenideElement PHONE_NUMBER_INPUT_FIELD = $(By.id("phone_number"));
    private static final SelenideElement PASSWORD_INPUT_FIELD = $(By.id("password"));
    private static final SelenideElement CONFIRM_PASSWORD_INPUT_FIELD = $(By.id("confirm_password"));
    private static final SelenideElement REGISTRATION_BUTTON = $x("//input[contains(@type, 'submit')]");

    public void fillLogin(String login) {
        LOGIN_INPUT_FIELD.setValue(login);
    }

    public void fillFullName(String fullName) {
        FULL_NAME_INPUT_FIELD.setValue(fullName);
    }

    public void fillFlatNumber(String flatNumber) {
        FLAT_NUMBER_INPUT_FIELD.setValue(flatNumber);
    }

    public void fillEmail(String email) {
        EMAIL_INPUT_FIELD.setValue(email);
    }

    public void fillPhoneNumber(String phoneNumber) {
        PHONE_NUMBER_INPUT_FIELD.setValue(phoneNumber);
    }

    public void fillPasswordFirst(String password) {
        PASSWORD_INPUT_FIELD.setValue(password);
    }

    public void fillPasswordConfirm(String confirmPassword) {
        CONFIRM_PASSWORD_INPUT_FIELD.setValue(confirmPassword);
    }

    public void clickRegistrationButton() {
        REGISTRATION_BUTTON.click();
    }
}
