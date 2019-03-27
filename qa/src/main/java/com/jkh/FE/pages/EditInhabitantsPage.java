package com.jkh.FE.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.jkh.BE.models.enums.Status;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.*;

@Component
public class EditInhabitantsPage {

    //title
    private static final SelenideElement WELCOME_TITLE = $x("//p");
    //buttons
    private static final SelenideElement LOGOUT_BUTTON = findButton("Выйти");
    private static final SelenideElement BACK_TO_ADMIN_PAGE_BUTTON = findButton("Вернуться на главную");
    private static final SelenideElement SHOW_USER_BUTTON = findInput("Посмотреть пользователей");
    private static final SelenideElement SAVE_USER_BUTTON = findInput("Сохранить изменения");
    //flat number input field
    private static final SelenideElement FLAT_NUMBER_INPUT_FIELD = $(By.id("flat"));
    //table rows
    private static final ElementsCollection ROWS = $$x("//tbody//tr");
    //table columns
    private static final ElementsCollection COLUMNS = $$x("//tbody//tr//td");
    private static final SelenideElement NAME_COLUMN = COLUMNS.get(0);
    private static final SelenideElement LOGIN_COLUMN = COLUMNS.get(1);
    private static final SelenideElement PHONE_COLUMN = COLUMNS.get(2);
    private static final SelenideElement EMAIL_COLUMN = COLUMNS.get(3);
    private static final SelenideElement RADIOS = COLUMNS.get(4);
    //status radio
    private static final ElementsCollection ACTIVE_STATUS_RADIO = findRadioButton(Status.ACTIVE.toString());
    private static final ElementsCollection INACTIVE_STATUS_RADIO = findRadioButton(Status.INACTIVE.toString());
    private static final ElementsCollection UNVERIFIED_STATUS_RADIO = findRadioButton(Status.UNVERIFIED.toString());
    private static final ElementsCollection REMOVED_STATUS_RADIO = findRadioButton(Status.REMOVED.toString());
    //error messages
    private static final SelenideElement FLAT_NOT_FOUND_ERROR_MESSAGE = findErrorMessage1(Error.FLAT_NOT_FOUND);
    private static final SelenideElement INCORRECT_FLAT_ERROR_MESSAGE = findErrorMessage2(Error.INCORRECT_FLAT);
    private static final SelenideElement INCORRECT_STATUSES_ERROR_MESSAGE = findErrorMessage1(Error.INCORRECT_STATUSES);

    private static SelenideElement findButton(String text) {
        return $x(String.format("//a[contains(text(), '%s')]", text));
    }

    private static SelenideElement findInput(String value) {
        return $x(String.format("//input[contains(@value, '%s')]", value));
    }

    private static SelenideElement findErrorMessage1(Error error) {
        return $x(String.format("//p[contains(text(), '%s')]", error.getMessage()));
    }

    private static SelenideElement findErrorMessage2(Error error) {
        return $x(String.format("//div[(text()='%s')]", error.getMessage()));
    }

    private static ElementsCollection findRadioButton(String value) {
        return $$x(String.format("//input[(@value='%s')]", value));
    }

    public void clickButton(Button button) {
        switch (button) {
            case LOGOUT:
                LOGOUT_BUTTON.click();
                break;
            case BACK_TO_ADMIN_PAGE:
                BACK_TO_ADMIN_PAGE_BUTTON.click();
                break;
            case SHOW_USER:
                SHOW_USER_BUTTON.click();
                break;
            case SAVE_USER:
                SAVE_USER_BUTTON.click();
                break;
        }
    }

    public Boolean getErrorMessageVisibility(Error error) {
        switch (error) {
            case FLAT_NOT_FOUND:
                return FLAT_NOT_FOUND_ERROR_MESSAGE.isDisplayed();
            case INCORRECT_FLAT:
                return INCORRECT_FLAT_ERROR_MESSAGE.isDisplayed();
            case INCORRECT_STATUSES:
                return INCORRECT_STATUSES_ERROR_MESSAGE.isDisplayed();
            default:
                return false;
        }
    }

    public void fillFlatNumberInputField(String flat) {
        FLAT_NUMBER_INPUT_FIELD.setValue(flat);
    }

    public String getName(Integer id) {
        return COLUMNS.get(id * 5).text();
    }

    public String getLogin(Integer id) {
        return COLUMNS.get(id * 5 + 1).text();
    }

    public String getPhone(Integer id) {
        return COLUMNS.get(id * 5 + 2).text();
    }

    public String getEmail(Integer id) {
        return COLUMNS.get(id * 5 + 3).text();
    }

    public void selectStatusRadio(Integer id, Status status) {
        switch (status) {
            case ACTIVE:
                ACTIVE_STATUS_RADIO.get(id).click();
                break;
            case INACTIVE:
                INACTIVE_STATUS_RADIO.get(id).click();
                break;
            case UNVERIFIED:
                UNVERIFIED_STATUS_RADIO.get(id).click();
                break;
            case REMOVED:
                REMOVED_STATUS_RADIO.get(id).click();
                break;
        }
    }

    public enum Button {
        LOGOUT,
        BACK_TO_ADMIN_PAGE,
        SHOW_USER,
        SAVE_USER
    }

    public void reset() {
        WELCOME_TITLE.click();
    }

    public enum Error {

        FLAT_NOT_FOUND("Квартира не найдена"),
        INCORRECT_FLAT("Некорректное значение."),
        INCORRECT_STATUSES("Блок статусов некорректен в квартире");

        private String message;

        Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
