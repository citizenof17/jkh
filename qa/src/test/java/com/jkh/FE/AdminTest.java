package com.jkh.FE;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.pages.AdminPage;
import com.jkh.FE.steps.AdminPageSteps;
import com.jkh.FE.steps.BaseSteps;
import com.jkh.FE.steps.LoginPageSteps;
import com.jkh.utils.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jkh.utils.TestConstants.*;

@Features("FrontEnd")
@Stories("AdminPage UI tests")
@ContextConfiguration(classes = ConfigurationMain.class)
public class AdminTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Autowired
    private AuthSteps authSteps;

    @Autowired
    private LoginPageSteps loginPageSteps;

    @Autowired
    private AdminPageSteps adminPageSteps;

    @Autowired
    private BaseSteps baseSteps;

    @BeforeClass(groups = {"FE", "Admin"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        for (Object[] objects : correctRegisterData) {
            authSteps.registerUser((RegisterRequest) objects[0]);
        }
    }

    @BeforeMethod(groups = {"FE", "Admin"})
    public void openAdminPage() {
        loginPageSteps.openLoginPage();
        loginPageSteps.fillCorrectCredential(ADMIN.getLogin(), ADMIN.getPassword());
    }

    @Test(groups = {"FE", "Admin"})
    @Title("Checking inaccessibility admin page without authorization")
    public void inaccessibilityHomePageTest() {
        baseSteps.deleteAllCookies();
        loginPageSteps.openLoginPage();
        adminPageSteps.openAdminPage();
        Assertions.checkAddress(LOGIN_PAGE_ADDRESS);
    }

    @Test(groups = {"FE", "Admin"})
    @Title("Checking redirecting on \"login\" page")
    public void logoutTest() {
        adminPageSteps.clickButton(AdminPage.Button.LOGOUT);
        Assertions.checkAddress(LOGIN_PAGE_ADDRESS);
    }

    @Test(groups = {"FE", "Admin"})
    @Title("Checking redirecting on edit \"inhabitants\" page")
    public void editInhabitantsTest() {
        adminPageSteps.clickButton(AdminPage.Button.EDIT_INHABITANTS);
        Assertions.checkAddress(EDIT_INHABITANTS_ADDRESS);
    }

    @Test(groups = {"FE", "Admin"})
    @Title("Checking redirecting on \"did not send\" page")
    public void didNotSendTest() {
        adminPageSteps.clickButton(AdminPage.Button.DID_NOT_SEND);
        Assertions.checkAddress(DID_NOT_SEND_ADDRESS);
    }

    @Test(groups = {"FE", "Admin"})
    @Title("Checking redirecting on \"newcomers\" page")
    public void newcomersTest() {
        adminPageSteps.clickButton(AdminPage.Button.NEWCOMERS);
        Assertions.checkAddress(NEWCOMERS_ADDRESS);
    }
}
