package com.jkh.FE.steps;

import com.jkh.BE.models.Counter;
import com.jkh.BE.models.IndicationRequest;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.FE.pages.HomePage;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static com.jkh.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class HomePageSteps {

    @Autowired
    private HomePage homePage;

    @Autowired
    private BaseSteps baseSteps;

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Step("Opening Home page")
    public void openHomePage() {
        baseSteps.openPage(HOME_PAGE_ADDRESS);
    }

    @Step("Checking unverified title")
    public void checkUnverifiedTitle() {
        homePage.checkUnverifiedTitleDisplayed();
    }

    @Step("Checking admin name field contains: {0}")
    public void checkAdminNameField(String name) {
        Assertions.assertThat(homePage.getAdminName()).as("Incorrect admin name")
                .contains(String.format(HOME_ADMIN_NAME, name));
    }

    @Step("Checking admin email field contains: {0}")
    public void checkAdminEmailField(String email) {
        Assertions.assertThat(homePage.getAdminEmail()).as("Incorrect admin email")
                .contains(String.format(HOME_ADMIN_EMAIL, email));
    }

    @Step("Checking admin phone field contains: {0}")
    public void checkAdminPhoneField(String phone) {
        Assertions.assertThat(homePage.getAdminPhone()).as("Incorrect admin phone")
                .contains(String.format(HOME_ADMIN_PHONE, phone));
    }

    @Step("Checking UNVERIFIED home page fields")
    public void checkUnverifiedInfo() {
        checkUnverifiedTitle();
        checkAdminNameField(ADMIN.getName());
        checkAdminEmailField(ADMIN.getEmail());
        checkAdminPhoneField(ADMIN.getPhone());
    }

    @Step("Checking welcome title contains username: {0}")
    public void checkWelcomeTitle(String name) {
        assertThat(homePage.getWelcomeTitle()).as("Welcome title does not contain expected username")
                .contains(String.format(HOME_PAGE_WELCOME_TITLE, name));
    }

    @Step("Clicking on logout button")
    public void clickLogoutButton() {
        homePage.clickLogoutButton();
    }

    @Step("Filling {1} indication value on: {0}")
    public void fillIndication(String value, Counter.CounterType counterType) {
        homePage.fillIndication(value, counterType);
    }

    public void fillIndications(List<IndicationRequest> indicationRequests) {
        for (IndicationRequest indicationRequest : indicationRequests) {
            fillIndication(indicationRequest.getValue().toString(), indicationRequest.getCounter().getType());
        }
        clickSendIndicationsButton();
    }

    @Step("Checking sent indications")
    public void checkLastIndications(List<IndicationRequest> expectedIndications, String login) {
        SoftAssertions assertions = new SoftAssertions();
        for (IndicationRequest expectedIndication : expectedIndications) {
            Counter.CounterType counterType = expectedIndication.getCounter().getType();
            assertions.assertThat(dataLoadSteps.lastIndication(login, counterType))
                    .as(String.format("Incorrect %s value", counterType)).isEqualTo(expectedIndication.getValue());
        }
        assertions.assertAll();
    }

    @Step("Clicking on send indications button")
    public void clickSendIndicationsButton() {
        homePage.clickSendIndicationsButton();
    }

    @Step("Selecting time period radio on: {0}")
    public void selectPeriodRadio(HomePage.TimePeriod timePeriod) {
        homePage.selectPeriodRadio(timePeriod);
    }

    @Step("Clicking on make report button")
    public void clickMakeReportButton() {
        homePage.clickMakeReportButton();
    }
}
