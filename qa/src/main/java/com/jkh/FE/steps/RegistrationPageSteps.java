package com.jkh.FE.steps;

import com.jkh.FE.models.RegisterUser;
import com.jkh.FE.pages.RegistrationPage;
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
    public void fillRegistrationFields(RegisterUser registerUser) {
        fillLoginInputField(registerUser.getLogin());
        fillFullNameInputField(registerUser.getName());
        fillFlatNumberInputField(String.valueOf(registerUser.getFlat().getNumber()));
        fillEmailInputField(registerUser.getEmail());
        fillPhoneNumberInputField(registerUser.getPhone());
        fillPasswordInputField(registerUser.getPassword());
        fillPasswordConfirmInputField(registerUser.getConfirmPassword());
    }

    @Step("Clicking on registration button")
    public void clickRegistrationButton() {
        registrationPage.clickRegistrationButton();
    }
}
