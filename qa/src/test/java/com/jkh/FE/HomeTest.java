package com.jkh.FE;

import com.jkh.BE.models.IndicationRequest;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.steps.BaseSteps;
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

import java.util.ArrayList;
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

    @BeforeClass(groups = {"FE", "Home"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        for (Object[] objects : correctRegisterData) {
            authSteps.registerUser((RegisterRequest) objects[0]);
        }
    }

    @BeforeMethod(groups = {"FE", "Home"})
    public void openHomePage() {
        baseSteps.deleteAllCookies();
        loginPageSteps.openLoginPage();
    }

    @Test(groups = {"FE", "Home"})
    @Title("Clicking on logout button")
    public void logoutTest() {
        RegisterRequest correctUser = (RegisterRequest) correctRegisterData[0][0];
        loginPageSteps.fillCorrectCredential(correctUser.getLogin(), correctUser.getPassword());
        homePageSteps.clickLogoutButton();
        Assertions.checkAddress(LOGIN_PAGE_ADDRESS);
    }

    @Test(groups = {"FE", "Home"})
    @Title("Checking inaccessibility home page without authorization")
    public void inaccessibilityHomePageTest() {
        homePageSteps.openHomePage();
        Assertions.checkAddress(LOGIN_PAGE_ADDRESS);
    }

    @Test(groups = {"FE", "Home"}, dataProvider = "correctIndicationData")
    @Title("Checking sending correct indications data")
    public void sendCorrectIndications(RegisterRequest correctUser) {
        loginPageSteps.fillCorrectCredential(correctUser.getLogin(), correctUser.getPassword());
        homePageSteps.fillIndications((List<IndicationRequest>) correctIndicationData[0][0]);
        homePageSteps.checkLastIndications((List<IndicationRequest>) correctIndicationData[0][0], correctUser.getLogin());
    }

    @DataProvider(name = "correctIndicationData")
    public Object[] correctIndicationData() {
        ArrayList<RegisterRequest> registerRequests = new ArrayList<>();
        for (Object[] objects1 : correctRegisterData) {
            registerRequests.add((RegisterRequest) objects1[0]);
        }
        return registerRequests.subList(0, registerRequests.size() - 1).toArray();
    }
}
