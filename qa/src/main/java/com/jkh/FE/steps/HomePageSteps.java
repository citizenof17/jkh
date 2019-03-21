package com.jkh.FE.steps;

import com.jkh.FE.pages.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import static com.jkh.utils.TestConstants.HOME_PAGE_WELCOME_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class HomePageSteps {

    @Autowired
    private HomePage homePage;

    @Step("Checking welcome title contains username: {0}")
    public void checkWelcomeTitle(String name) {
        assertThat(homePage.getWelcomeTitle()).as("Welcome title does not contain expected username")
                .contains(String.format(HOME_PAGE_WELCOME_TITLE, name));
    }
}
