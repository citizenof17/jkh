package com.jkh.FE;

import com.jkh.BE.models.IndicationRequest;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.pages.HomePage;
import com.jkh.FE.steps.BaseSteps;
import com.jkh.FE.steps.EditInhabitantsPageSteps;
import com.jkh.FE.steps.HomePageSteps;
import com.jkh.FE.steps.LoginPageSteps;
import com.jkh.utils.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static com.jkh.utils.TestConstants.*;


@Features("FrontEnd")
@Stories("HomePage UI tests")
@ContextConfiguration(classes = ConfigurationMain.class)
public class HomeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Autowired
    private AuthSteps authSteps;

    @Autowired
    private HomePageSteps homePageSteps;

    @Autowired
    private LoginPageSteps loginPageSteps;

    @Autowired
    private BaseSteps baseSteps;

    @Autowired
    private EditInhabitantsPageSteps editInhabitantsPageSteps;

    @BeforeClass(groups = {"FE", "Home"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        for (RegisterRequest user : CORRECT_USERS) {
            authSteps.registerUser(user);
        }
        dataLoadSteps.updateAllUsersToActive();
    }

    @BeforeMethod(groups = {"FE", "Home"})
    public void openHomePage() {
        baseSteps.deleteAllCookies();
        loginPageSteps.openLoginPage();
    }

    @Test(groups = {"FE", "Home"})
    @Title("Clicking on logout button")
    public void logoutTest() {
        loginPageSteps.fillCorrectCredential(CORRECT_REGISTER_USER.getLogin(), CORRECT_REGISTER_USER.getPassword());
        homePageSteps.clickLogoutButton();
        Assertions.checkAddress(LOGIN_PAGE_ADDRESS);
    }

    @Test(groups = {"FE", "Home"})
    @Title("Checking inaccessibility home page without authorization")
    public void inaccessibilityHomePageTest() {
        baseSteps.deleteAllCookies();
        homePageSteps.openHomePage();
        Assertions.checkAddress(LOGIN_PAGE_ADDRESS);
    }

    @Test(groups = {"FE", "Home"}, dataProvider = "correctIndicationData")
    @Title("Checking sending correct indications data")
    public void sendCorrectIndicationsTest(RegisterRequest correctUser) {
        loginPageSteps.fillCorrectCredential(correctUser.getLogin(), correctUser.getPassword());
        homePageSteps.fillIndications((List<IndicationRequest>) correctIndicationData[0][0]);
        homePageSteps.checkLastIndications((List<IndicationRequest>) correctIndicationData[0][0], correctUser.getLogin());
        homePageSteps.checkMessageVisibility(HomePage.Message.SUCCESS_SENT_INDICATIONS, true);
    }


    @Test(groups = {"FE", "Home"}, dataProvider = "correctIndicationData")
    @Title("Checking report by last year")
    public void checkLastYearReportTest(RegisterRequest correctUser) {
        loginPageSteps.fillCorrectCredential(correctUser.getLogin(), correctUser.getPassword());
        for (Object[] indicationRequest : correctIndicationData) {
            homePageSteps.fillIndications((List<IndicationRequest>) indicationRequest[0]);
        }
        homePageSteps.selectPeriodRadio(HomePage.TimePeriod.THIS_YEAR);
        homePageSteps.clickMakeReportButton();
        homePageSteps.checkAllTableIndications(correctUser.getLogin());
    }

    @DataProvider(name = "correctIndicationData")
    public Object[] correctIndicationData() {
        return CORRECT_USERS.toArray();
    }
}
