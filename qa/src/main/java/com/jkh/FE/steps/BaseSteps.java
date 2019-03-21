package com.jkh.FE.steps;

import com.codeborne.selenide.Selenide;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class BaseSteps {

    @Value("${ui.address:http://localhost:4200}")
    private String address;

    @Step("Opening {0} page")
    public void openPage(String pageAddress) {
        Selenide.open(address + pageAddress);
    }
}
