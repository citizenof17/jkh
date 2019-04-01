package com.jkh.FE.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.jkh.utils.TestConstants.*;

@Component
public class AdminPage {

    //title
    private static final SelenideElement WELCOME_TITLE = $x("//p");
    //buttons
    private static final SelenideElement LOGOUT_BUTTON = $x("//a");
    private static final SelenideElement EDIT_INHABITANTS_BUTTON = findButton(EDIT_INHABITANTS_ADDRESS);
    private static final SelenideElement DID_NOT_SEND_BUTTON = findButton(DID_NOT_SEND_ADDRESS);
    private static final SelenideElement NEWCOMERS_BUTTON = findButton(NEWCOMERS_ADDRESS);
    private static final SelenideElement MAKE_REPORT_BUTTON = $x("//input[contains(@value, 'Построить отчет')]");
    //status radio
    private static final SelenideElement ALL_STATUSES_RADIO = findRadioButton(null);
    private static final SelenideElement ACTIVE_STATUS_RADIO = findRadioButton(UserStatus.ACTIVE.toString());
    private static final SelenideElement INACTIVE_STATUS_RADIO = findRadioButton(UserStatus.INACTIVE.toString());
    private static final SelenideElement REMOVED_STATUS_RADIO = findRadioButton(UserStatus.REMOVED.toString());
    //flat number input
    private static final SelenideElement FLAT_NUMBER_INPUT_FIELD = $(By.id("flatNum"));
    //period radio
    private static final SelenideElement ALL_PERIOD_RADIO = findRadioButton(TimePeriod.ALL.toString());
    private static final SelenideElement THIS_YEAR_RADIO = findRadioButton(TimePeriod.THIS_YEAR.toString());
    private static final SelenideElement THIS_MONTH_RADIO = findRadioButton(TimePeriod.THIS_MONTH.toString());
    private static final SelenideElement MANUAL_RADIO = findRadioButton(TimePeriod.MANUAL.toString());

    private static SelenideElement findButton(String href) {
        return $x(String.format("//a[contains(@ng-reflect-router-link, '%s')]", href));
    }

    private static SelenideElement findRadioButton(String value) {
        return $x(String.format("//input[contains(@value, '%s')]", value));
    }

    public String getWelcomeTitle() {
        return WELCOME_TITLE.text();
    }

    public void clickButton(Button button) {
        switch (button) {
            case LOGOUT:
                LOGOUT_BUTTON.click();
                break;
            case EDIT_INHABITANTS:
                EDIT_INHABITANTS_BUTTON.click();
                break;
            case NEWCOMERS:
                NEWCOMERS_BUTTON.click();
                break;
            case DID_NOT_SEND:
                DID_NOT_SEND_BUTTON.click();
                break;
            case MAKE_REPORT:
                MAKE_REPORT_BUTTON.click();
                break;
        }
    }

    public void selectUsersStatusRadio(UserStatus userStatus) {
        switch (userStatus) {
            case ALL:
                ALL_STATUSES_RADIO.click();
                break;
            case ACTIVE:
                ACTIVE_STATUS_RADIO.click();
                break;
            case INACTIVE:
                INACTIVE_STATUS_RADIO.click();
                break;
            case REMOVED:
                REMOVED_STATUS_RADIO.click();
                break;
        }
    }

    public void fillFlatNumber(String number) {
        FLAT_NUMBER_INPUT_FIELD.setValue(number);
    }

    public void selectPeriodRadio(TimePeriod timePeriod) {
        switch (timePeriod) {
            case ALL:
                ALL_PERIOD_RADIO.click();
                break;
            case THIS_YEAR:
                THIS_YEAR_RADIO.click();
                break;
            case THIS_MONTH:
                THIS_MONTH_RADIO.click();
                break;
            case MANUAL:
                MANUAL_RADIO.click();
                break;
        }
    }

    public void clickMakeReportButton() {
        MAKE_REPORT_BUTTON.click();
    }

    public enum Button {
        LOGOUT,
        EDIT_INHABITANTS,
        DID_NOT_SEND,
        NEWCOMERS,
        MAKE_REPORT
    }

    public enum UserStatus {
        ALL,
        ACTIVE,
        INACTIVE,
        REMOVED
    }

    public enum TimePeriod {
        ALL,
        THIS_YEAR,
        THIS_MONTH,
        MANUAL
    }
}
