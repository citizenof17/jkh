package com.jkh.FE.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.jkh.BE.models.enums.Status;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.*;

@Component
public class EditInhabitantsPage {

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

    private static SelenideElement findButton(String text) {
        return $x(String.format("//a[contains(text(), '%s')]", text));
    }

    private static SelenideElement findInput(String value) {
        return $x(String.format("//input[contains(@value, '%s')]", value));
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
        switch(status) {
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
}
