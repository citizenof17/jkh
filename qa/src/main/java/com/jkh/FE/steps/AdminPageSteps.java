package com.jkh.FE.steps;

import com.jkh.FE.pages.AdminPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import static com.jkh.utils.TestConstants.ADMIN_PAGE_ADDRESS;
import static com.jkh.utils.TestConstants.ADMIN_PAGE_WELCOME_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class AdminPageSteps {

    @Autowired
    private AdminPage adminPage;

    @Autowired
    private BaseSteps baseSteps;

    @Step("Opening Admin page")
    public void openAdminPage() {
        baseSteps.openPage(ADMIN_PAGE_ADDRESS);
    }

    @Step("Checking welcome title contains username: {0}")
    public void checkWelcomeTitle(String name) {
        assertThat(adminPage.getWelcomeTitle()).as("Admin welcome title does not contain expected admin name")
                .contains(String.format(ADMIN_PAGE_WELCOME_TITLE, name));
    }

    @Step("Clicking on {0} button")
    public void clickButton(AdminPage.Button button) {
        adminPage.clickButton(button);
    }
}
