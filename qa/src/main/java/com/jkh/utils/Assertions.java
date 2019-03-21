package com.jkh.utils;

import com.codeborne.selenide.WebDriverRunner;
import ru.yandex.qatools.allure.annotations.Step;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Assertions {

    public static void compareIfEqual(Object actual, Object expected) {
        assertThat(actual).isEqualTo(expected);
    }

    @Step("Checking that {0} page is opened")
    public static void checkAddress(String expectedAddress) {
        assertThat(WebDriverRunner.url()).as("Wrong URL").contains(expectedAddress);
    }
}
