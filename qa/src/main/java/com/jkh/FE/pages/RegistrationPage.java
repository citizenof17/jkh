package com.jkh.FE.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class RegistrationPage {

    private static final String LOGIN_ERROR_TEXT = "Некорректный формат имени пользователя.";
    private static final String NAME_ERROR_TEXT = "Некорректный формат Ф.И.О.";
    private static final String FLAT_ERROR_TEXT = "Некорректный номер квартиры.";
    private static final String EMAIL_ERROR_TEXT = "Некорректный формат адреса электронной почты.";
    private static final String PHONE_ERROR_TEXT = "Некорректный формат номера телефона.";
    private static final String PASSWORD_ERROR_TEXT = "Некорректный формат пароля.";
    private static final String CONFIRM_PASSWORD_ERROR_TEXT = "Введенные пароли не совпадают.";

    private static final String REPEAT_LOGIN_ERROR_TEXT = "Пользователь с таким логином уже существует";
    private static final String REPEAT_EMAIL_ERROR_TEXT = "Пользователь с таким адресом электронной почты уже существует";
    private static final String REPEAT_PHONE_ERROR_TEXT = "Пользователь с таким номером телефона уже существует";

    private static final SelenideElement LOGIN_INPUT_FIELD = $(By.id("username"));
    private static final SelenideElement FULL_NAME_INPUT_FIELD = $(By.id("fullname"));
    private static final SelenideElement FLAT_NUMBER_INPUT_FIELD = $(By.id("flatnumber"));
    private static final SelenideElement EMAIL_INPUT_FIELD = $(By.id("email"));
    private static final SelenideElement PHONE_NUMBER_INPUT_FIELD = $(By.id("phonenumber"));
    private static final SelenideElement PASSWORD_INPUT_FIELD = $(By.id("password"));
    private static final SelenideElement CONFIRM_PASSWORD_INPUT_FIELD = $(By.id("confirmpassword"));
    private static final SelenideElement REGISTRATION_BUTTON = $x("//input[contains(@type, 'submit')]");

    private static final SelenideElement LOGIN_ERROR_MESSAGE = findErrorMessage(LOGIN_ERROR_TEXT);
    private static final SelenideElement NAME_ERROR_MESSAGE = findErrorMessage(NAME_ERROR_TEXT);
    private static final SelenideElement FLAT_ERROR_MESSAGE = findErrorMessage(FLAT_ERROR_TEXT);
    private static final SelenideElement EMAIL_ERROR_MESSAGE = findErrorMessage(EMAIL_ERROR_TEXT);
    private static final SelenideElement PHONE_ERROR_MESSAGE = findErrorMessage(PHONE_ERROR_TEXT);
    private static final SelenideElement PASSWORD_ERROR_MESSAGE = findErrorMessage(PASSWORD_ERROR_TEXT);
    private static final SelenideElement CONFIRM_PASSWORD_ERROR_MESSAGE = findErrorMessage(CONFIRM_PASSWORD_ERROR_TEXT);

    private static final SelenideElement REPEAT_LOGIN_ERROR_MESSAGE = findRepeatErrorMessage(REPEAT_LOGIN_ERROR_TEXT);
    private static final SelenideElement REPEAT_EMAIL_ERROR_MESSAGE = findRepeatErrorMessage(REPEAT_EMAIL_ERROR_TEXT);
    private static final SelenideElement REPEAT_PHONE_ERROR_MESSAGE = findRepeatErrorMessage(REPEAT_PHONE_ERROR_TEXT);

    private static SelenideElement findRepeatErrorMessage(String message) {
        return $x(String.format("//p[contains(text(), '%s')]", message));
    }

    private static SelenideElement findErrorMessage(String message) {
        return $x(String.format("//div[contains(text(), '%s')]", message));
    }

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

    public Boolean getLoginErrorMessageVisibility() {
        return LOGIN_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getNameErrorMessageVisibility() {
        return NAME_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getFlatErrorMessageVisibility() {
        return FLAT_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getEmailErrorMessageVisibility() {
        return EMAIL_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getPhoneErrorMessageVisibility() {
        return PHONE_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getPasswordErrorMessageVisibility() {
        return PASSWORD_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getConfirmPasswordErrorMessageVisibility() {
        return CONFIRM_PASSWORD_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getRepeatLoginErrorMessageVisibility() {
        return REPEAT_LOGIN_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getRepeatEmailErrorMessageVisibility() {
        return REPEAT_EMAIL_ERROR_MESSAGE.isDisplayed();
    }

    public Boolean getRepeatPhoneErrorMessageVisibility() {
        return REPEAT_PHONE_ERROR_MESSAGE.isDisplayed();
    }
}
