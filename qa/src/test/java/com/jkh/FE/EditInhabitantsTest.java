package com.jkh.FE;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.models.enums.Status;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.pages.EditInhabitantsPage;
import com.jkh.FE.steps.EditInhabitantsPageSteps;
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
import java.util.Map;

import static com.jkh.utils.TestConstants.CORRECT_REGISTER_USER;
import static com.jkh.utils.TestConstants.correctRegisterData;

@Features("FrontEnd")
@Stories("EditInhabitantsPage UI tests")
@ContextConfiguration(classes = ConfigurationMain.class)
public class EditInhabitantsTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataLoadSteps dataLoadSteps;

    @Autowired
    private AuthSteps authSteps;

    @Autowired
    private EditInhabitantsPageSteps editInhabitantsPageSteps;

    @BeforeClass(groups = {"FE", "Edit"})
    public void prepareData() throws Exception {
        dataLoadSteps.deleteAllData();
        for (Object[] objects : correctRegisterData) {
            authSteps.registerUser((RegisterRequest) objects[0]);
        }
    }

    @BeforeMethod(groups = {"FE", "Edit"})
    public void openEditInhabitantsPage() {
        editInhabitantsPageSteps.openEditInhabitantsPage();
    }

    @Test(groups = {"FE", "Edit"}, dataProvider = "correctUserData")
    @Title("Checking show user by flat service")
    public void checkShowUserTest(List<Map<String, Object>> correctUsers) {
        editInhabitantsPageSteps.fillFlatInputField(String.valueOf(correctUsers.get(0).get("number")));
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SHOW_USER);
        editInhabitantsPageSteps.checkAllFields(correctUsers);
    }

    @Test(groups = {"FE", "Edit"})
    @Title("Checking editing user status on ACTIVE")
    public void checkRadioTest() {
        editInhabitantsPageSteps.fillFlatInputField(String.valueOf(CORRECT_REGISTER_USER.getFlat().getNumber()));
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SHOW_USER);
        editInhabitantsPageSteps.selectStatusRadio(0, Status.ACTIVE);
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SAVE_USER);
        dataLoadSteps.checkUserStatus(CORRECT_REGISTER_USER.getLogin(), Status.ACTIVE);
    }

    @DataProvider(name = "correctUserData")
    public Object[] correctUserData() {
        return dataLoadSteps.listsUsersByFlat().toArray();
    }
}
