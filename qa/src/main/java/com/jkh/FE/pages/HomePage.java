package com.jkh.FE.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.jkh.BE.models.Counter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

@Component
public class HomePage {

    //table columns
    public static final ElementsCollection COLUMNS = $$x("//tbody//tbody//tr//td");
    //titles
    private static final SelenideElement WELCOME_TITLE = $x("//p");
    //admin info
    private static final SelenideElement ADMIN_NAME_FIELD = findField("Имя:");
    private static final SelenideElement ADMIN_EMAIL_FIELD = findField("Электронная почта:");
    private static final SelenideElement ADMIN_PHONE_FIELD = findField("Телефон:");
    //buttons
    private static final SelenideElement LOGOUT_BUTTON = $x("//a");
    private static final SelenideElement SEND_INDICATIONS_BUTTON = $x("//input[contains(@value, 'Отправить показания')]");
    private static final SelenideElement MAKE_REPORT_BUTTON = $x("//input[contains(@value, 'Построить отчет')]");
    //indication input fields
    private static final SelenideElement ELECTRICITY_INPUT_FIELD = $(By.id("electr"));
    private static final SelenideElement HOT_WATER_INPUT_FIELD = $(By.id("hot"));
    private static final SelenideElement COLD_WATER_INPUT_FIELD = $(By.id("cold"));
    //period radio
    private static final SelenideElement ALL_RADIO = findPeriodRadioButton(TimePeriod.ALL);
    private static final SelenideElement THIS_YEAR_RADIO = findPeriodRadioButton(TimePeriod.THIS_YEAR);
    private static final SelenideElement THIS_MONTH_RADIO = findPeriodRadioButton(TimePeriod.THIS_MONTH);
    private static final SelenideElement MANUAL_RADIO = findPeriodRadioButton(TimePeriod.MANUAL);
    //messages
    private static final SelenideElement SUCCESS_SENT_INDICATIONS_MESSAGE = findField(Message.SUCCESS_SENT_INDICATIONS.getMessage());
    private static String UNVERIFIED_TITLE_TEXT = "Ваш аккаунт не был подтвержден. Для подтверждения свяжитесь с администратором. ";
    private static final SelenideElement UNVERIFIED_TITLE = findField(UNVERIFIED_TITLE_TEXT);

    private static SelenideElement findField(String title) {
        return $x(String.format("//p[contains(text(), '%s')]", title));
    }

    private static SelenideElement findPeriodRadioButton(TimePeriod timePeriod) {
        return $x(String.format("//input[contains(@value, '%s')]", timePeriod));
    }

    public void checkUnverifiedTitleDisplayed() {
        Assertions.assertThat(UNVERIFIED_TITLE.isDisplayed()).as("Wrong Home page Redirect").isTrue();
    }

    public Boolean getMessageVisibility(Message message) {
        switch (message) {
            case SUCCESS_SENT_INDICATIONS:
                return SUCCESS_SENT_INDICATIONS_MESSAGE.isDisplayed();
            default:
                return false;
        }
    }

    public String getWelcomeTitle() {
        return WELCOME_TITLE.text();
    }

    public String getAdminName() {
        return ADMIN_NAME_FIELD.text();
    }

    public String getAdminEmail() {
        return ADMIN_EMAIL_FIELD.text();
    }

    public String getAdminPhone() {
        return ADMIN_PHONE_FIELD.text();
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

    public Map<String, String> getIndication(Integer id) {
        HashMap<String, String> indication = new HashMap<>();
        indication.put("date", COLUMNS.get(id * 4).text().replace("\"", ""));
        indication.put("electr", COLUMNS.get(id * 4 + 1).text().replace("\"", ""));
        indication.put("hot", COLUMNS.get(id * 4 + 2).text());
        indication.put("cold", COLUMNS.get(id * 4 + 3).text().replace("\"", ""));
        return indication;
    }

    public enum Message {

        SUCCESS_SENT_INDICATIONS("Данные успешно отправлены");

        private String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public enum TimePeriod {
        ALL,
        THIS_YEAR,
        THIS_MONTH,
        MANUAL
    }
}
