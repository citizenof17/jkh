package com.jkh.FE.pages;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;


@Component
public class HomePage {

    private static final SelenideElement WELCOME_TITLE = $x("//p");

    public String getWelcomeTitle() {
        return WELCOME_TITLE.text();
    }
}
