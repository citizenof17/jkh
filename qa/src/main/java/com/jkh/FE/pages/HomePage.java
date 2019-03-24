package com.jkh.FE.pages;

import com.codeborne.selenide.SelenideElement;
import com.jkh.BE.models.Counter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@Component
public class HomePage {

    private static final SelenideElement WELCOME_TITLE = $x("//p");
    private static final SelenideElement LOGOUT_BUTTON = $x("//a");
    private static final SelenideElement ELECTRICITY_INPUT_FIELD = $(By.id("electr"));
    private static final SelenideElement HOT_WATER_INPUT_FIELD = $(By.id("hot"));
    private static final SelenideElement COLD_WATER_INPUT_FIELD = $(By.id("cold"));
    private static final SelenideElement SEND_INDICATIONS_BUTTON = $x("//button");
    private static final SelenideElement ALL_RADIO = findPeriodRadioButton(TimePeriod.ALL);
    private static final SelenideElement THIS_YEAR_RADIO = findPeriodRadioButton(TimePeriod.THIS_YEAR);
    private static final SelenideElement THIS_MONTH_RADIO = findPeriodRadioButton(TimePeriod.THIS_MONTH);
    private static final SelenideElement MANUAL_RADIO = findPeriodRadioButton(TimePeriod.MANUAL);
    private static final SelenideElement MAKE_REPORT_BUTTON = $x("//input[contains(@value, 'Построить отчет')]");

    private static SelenideElement findPeriodRadioButton(TimePeriod timePeriod){
        return $x(String.format("//input[contains(@value, '%s')]", timePeriod));
    }

    public String getWelcomeTitle() {
        return WELCOME_TITLE.text();
    }

    public void clickLogoutButton() {
        LOGOUT_BUTTON.click();
    }

    public void fillIndication(String value, Counter.CounterType type) {
        switch (type) {
            case ELECTRICITY:
                ELECTRICITY_INPUT_FIELD.setValue(value);
                break;
            case HOT_WATER:
                HOT_WATER_INPUT_FIELD.setValue(value);
                break;
            case COLD_WATER:
                COLD_WATER_INPUT_FIELD.setValue(value);
                break;
        }
    }

    public void clickSendIndicationsButton() {
        SEND_INDICATIONS_BUTTON.click();
    }

    public void selectPeriodRadio(TimePeriod timePeriod) {
        switch (timePeriod) {
            case ALL:
                ALL_RADIO.click();
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

    public enum TimePeriod {
        ALL,
        THIS_YEAR,
        THIS_MONTH,
        MANUAL
    }
}
