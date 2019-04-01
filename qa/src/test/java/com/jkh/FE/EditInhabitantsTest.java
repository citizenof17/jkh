package com.jkh.FE;

import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.models.enums.Status;
import com.jkh.BE.steps.AuthSteps;
import com.jkh.BE.steps.DataLoadSteps;
import com.jkh.ConfigurationMain;
import com.jkh.FE.pages.EditInhabitantsPage;
import com.jkh.FE.steps.EditInhabitantsPageSteps;
import org.apache.commons.lang3.EnumUtils;
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

import static com.jkh.utils.TestConstants.*;

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
    @Title("Checking show FLAT_NOT_FOUND error message")
    public void checkFlatNotFoundErrorMessageTest() {
        editInhabitantsPageSteps.fillFlatInputField(NOT_FOUND_FLAT);
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SHOW_USER);
        editInhabitantsPageSteps.checkErrorMessageVisibility(EditInhabitantsPage.Error.FLAT_NOT_FOUND, true);
    }

    @Test(groups = {"FE", "Edit"}, dataProvider = "incorrectFlatData")
    @Title("Checking show INCORRECT_FLAT error message")
    public void checkIncorrectFlatErrorMessageTest(Integer flat) {
        editInhabitantsPageSteps.fillFlatInputField(String.valueOf(flat));
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SHOW_USER);
        editInhabitantsPageSteps.reset();
        editInhabitantsPageSteps.checkErrorMessageVisibility(EditInhabitantsPage.Error.INCORRECT_FLAT, true);
    }

    @Test(groups = {"FE", "Edit"}, dataProvider = "incorrectStatusesForFlat55Data")
    @Title("Checking show INCORRECT_STATUSES error message")
    public void checkIncorrectStatusesErrorMessageTest(List<Status> statuses) {
        editInhabitantsPageSteps.fillFlatInputField(String.valueOf(55));
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SHOW_USER);
        for (int i = 0; i < statuses.size(); i++) {
            editInhabitantsPageSteps.selectStatusRadio(i, statuses.get(i));
        }
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SAVE_USER);
        editInhabitantsPageSteps.checkErrorMessageVisibility(EditInhabitantsPage.Error.INCORRECT_STATUSES, true);
    }

    @Test(groups = {"FE", "Edit"}, dataProvider = "statusesData")
    @Title("Checking editing user status")
    public void checkRadioTest(Status status) {
        editInhabitantsPageSteps.fillFlatInputField(String.valueOf(CORRECT_REGISTER_USER.getFlat().getNumber()));
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SHOW_USER);
        editInhabitantsPageSteps.selectStatusRadio(0, status);
        editInhabitantsPageSteps.clickButton(EditInhabitantsPage.Button.SAVE_USER);
        editInhabitantsPageSteps.checkErrorMessageVisibility(EditInhabitantsPage.Error.SUCCESS, true);
        dataLoadSteps.checkUserStatus(CORRECT_REGISTER_USER.getLogin(), status);
    }

    @DataProvider(name = "correctUserData")
    public Object[] correctUserData() {
        return dataLoadSteps.listsUsersByFlat().toArray();
    }

    @DataProvider(name = "incorrectFlatData")
    public Object[] incorrectFlatData() {
        return incorrectFlatData;
    }

    @DataProvider(name = "statusesData")
    public Object[] statusesData() {
        return EnumUtils.getEnumList(Status.class).subList(0, 3).toArray();
    }

    @DataProvider(name = "incorrectStatusesForFlat55Data")
    public Object[] incorrectStatusesForFlat55Data() {
        return incorrectStatusesForFlat55Data;
    }
}
