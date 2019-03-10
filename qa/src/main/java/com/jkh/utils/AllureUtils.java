package com.jkh.utils;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

public class AllureUtils {

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveText(String attachmentName, String request) {
        return request;
    }

    @Attachment(type = "image/png")
    public static byte[] allureScreenshot(int timeout) throws IOException {
        WaiterUtils.wait(timeout);
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }
}