package com.jkh.FE.steps;

import com.jkh.BE.models.enums.Status;
import com.jkh.FE.ConfigurationFE;
import com.jkh.FE.pages.AdminPage;
import com.jkh.FE.pages.EditInhabitantsPage;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.Map;

@Component
public class EditInhabitantsPageSteps {

    @Autowired
    private EditInhabitantsPage editInhabitantsPage;

    @Autowired
    private LoginPageSteps loginPageSteps;

    @Autowired
    private AdminPageSteps adminPageSteps;

    @Autowired
    private ConfigurationFE configurationFE;

    @Value("${ADMIN_LOGIN:Administrator}")
    private String login;

    @Value("${ADMIN_PASSWORD:Administrator_1}")
    private String password;

    @Step("Open Edit Inhabitants page")
    public void openEditInhabitantsPage() {
        loginPageSteps.openLoginPage();
        loginPageSteps.fillCorrectCredential(login, password);
        adminPageSteps.clickButton(AdminPage.Button.EDIT_INHABITANTS);
    }

    @Step("Clicking on {0} button")
    public void clickButton(EditInhabitantsPage.Button button) {
        editInhabitantsPage.clickButton(button);
    }

    @Step("Filling flat number input field on: {0}")
    public void fillFlatInputField(String flat) {
        editInhabitantsPage.fillFlatNumberInputField(flat);
    }

    @Step("Select {0} user status on {1}")
    public void selectStatusRadio(Integer id, Status status) {
        editInhabitantsPage.selectStatusRadio(id, status);
    }

    public void checkAllFields(List<Map<String, Object>> correctUsers) {
        for (int i = 0; i < correctUsers.size(); i++) {
            checkUser(i, correctUsers.get(i));
        }
    }

    @Step("Checking user {0}")
    public void checkUser(Integer id, Map<String, Object> correctUser) {
        checkName(id, (String) correctUser.get("name"));
        checkLogin(id, (String) correctUser.get("login"));
        checkPhone(id, (String) correctUser.get("phone"));
        checkEmail(id, (String) correctUser.get("email"));
    }

    @Step("Checking username: {1}")
    public void checkName(Integer id, String name) {
        Assertions.assertThat(editInhabitantsPage.getName(id)).as("Wrong username").isEqualTo(name);
    }

    @Step("Checking login: {1}")
    public void checkLogin(Integer id, String login) {
        Assertions.assertThat(editInhabitantsPage.getLogin(id)).as("Wrong login").isEqualTo(login);
    }

    @Step("Checking phone number: {1}")
    public void checkPhone(Integer id, String phone) {
        Assertions.assertThat(editInhabitantsPage.getPhone(id)).as("Wrong phone").isEqualTo(phone);
    }

    @Step("Checking email: {1}")
    public void checkEmail(Integer id, String email) {
        Assertions.assertThat(editInhabitantsPage.getEmail(id)).as("Wrong email").isEqualTo(email);
    }

    @Step("Checking {0} error message is visible: {1}")
    public void checkErrorMessageVisibility(EditInhabitantsPage.Error error, boolean visibility) {
        Assertions.assertThat(editInhabitantsPage.getErrorMessageVisibility(error)).as("Error message \"" +
                error.getMessage() + "\" visibility is not equal: " + visibility).isEqualTo(visibility);
    }

    public void reset() {
        editInhabitantsPage.reset();
    }
}
