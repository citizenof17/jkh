package com.jkh.FE.steps;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.FE.models.RegisterUser;
import com.jkh.FE.pages.RegistrationPage;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class RegistrationPageSteps {

    private final RegistrationPage registrationPage;

    @Autowired
    public RegistrationPageSteps(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }

    @Step("Filling login input field on: {0}")
    public void fillLoginInputField(String login) {
        registrationPage.fillLogin(login);
    }

    @Step("Filling fullName input field on: {0}")
    public void fillFullNameInputField(String fullName) {
        registrationPage.fillFullName(fullName);
    }

    @Step("Filling flat number input field on: {0}")
    public void fillFlatNumberInputField(String flatNumber) {
        registrationPage.fillFlatNumber(flatNumber);
    }

    @Step("Filling email input field on: {0}")
    public void fillEmailInputField(String email) {
        registrationPage.fillEmail(email);
    }

    @Step("Filling phone number input field on: {0}")
    public void fillPhoneNumberInputField(String phoneNumber) {
        registrationPage.fillPhoneNumber(phoneNumber);
    }

    @Step("Filling password input field on: {0}")
    public void fillPasswordInputField(String password) {
        registrationPage.fillPasswordFirst(password);
    }

    @Step("Filling confirm password input field on: {0}")
    public void fillPasswordConfirmInputField(String confirmPassword) {
        registrationPage.fillPasswordConfirm(confirmPassword);
    }

    @Step("Filling all registration field")
    public void fillRegistrationFields(RegisterRequest registerUser) {
        fillLoginInputField(registerUser.getLogin());
        fillFullNameInputField(registerUser.getName());
        fillFlatNumberInputField(String.valueOf(registerUser.getFlat().getNumber()));
        fillEmailInputField(registerUser.getEmail());
        fillPhoneNumberInputField(registerUser.getPhone());
        fillPasswordInputField(registerUser.getPassword());
        fillPasswordConfirmInputField(registerUser.getPassword());
    }

    @Step("Filling all registration field")
    public void fillRegistrationFields(RegisterRequest registerUser, String confirmPassword) {
        fillLoginInputField(registerUser.getLogin());
        fillFullNameInputField(registerUser.getName());
        fillFlatNumberInputField(String.valueOf(registerUser.getFlat().getNumber()));
        fillEmailInputField(registerUser.getEmail());
        fillPhoneNumberInputField(registerUser.getPhone());
        fillPasswordInputField(registerUser.getPassword());
        fillPasswordConfirmInputField(confirmPassword);
    }

    @Step("Clicking on registration button")
    public void clickRegistrationButton() {
        registrationPage.clickRegistrationButton();
    }

    @Step("Checking that login error message is visible")
    public void checkLoginErrorMessage() {
        Assertions.assertThat(registrationPage.getLoginErrorMessageVisibility()).as("Login error message is not visible").isTrue();
    }

    @Step("Checking that name error message is visible")
    public void checkNameErrorMessage() {
        Assertions.assertThat(registrationPage.getNameErrorMessageVisibility()).as("Name error message is not visible").isTrue();
    }

    @Step("Checking that flat error message is visible")
    public void checkFlatErrorMessage() {
        Assertions.assertThat(registrationPage.getFlatErrorMessageVisibility()).as("Flat error message is not visible").isTrue();
    }

    @Step("Checking that email error message is visible")
    public void checkEmailErrorMessage() {
        Assertions.assertThat(registrationPage.getEmailErrorMessageVisibility()).as("Email error message is not visible").isTrue();
    }

    @Step("Checking that phone error message is visible")
    public void checkPhoneErrorMessage() {
        Assertions.assertThat(registrationPage.getPhoneErrorMessageVisibility()).as("Phone error message is not visible").isTrue();
    }

    @Step("Checking that password error message is visible")
    public void checkPasswordErrorMessage() {
        Assertions.assertThat(registrationPage.getPasswordErrorMessageVisibility()).as("Password error message is not visible").isTrue();
    }

    @Step("Checking that confirm password error message is visible")
    public void checkConfirmPasswordErrorMessage() {
        Assertions.assertThat(registrationPage.getConfirmPasswordErrorMessageVisibility()).as("Confirm password error message is not visible").isTrue();
    }

    @Step("Checking that repeat login error message is visible")
    public void checkRepeatLoginErrorMessage() {
        Assertions.assertThat(registrationPage.getRepeatLoginErrorMessageVisibility()).as("Repeat login error message is not visible").isTrue();
    }

    @Step("Checking that repeat email error message is visible")
    public void checkRepeatEmailErrorMessage() {
        Assertions.assertThat(registrationPage.getRepeatEmailErrorMessageVisibility()).as("Repeat email error message is not visible").isTrue();
    }

    @Step("Checking that repeat phone error message is visible")
    public void checkRepeatPhoneErrorMessage() {
        Assertions.assertThat(registrationPage.getRepeatPhoneErrorMessageVisibility()).as("Repeat phone error message is not visible").isTrue();
    }
}
