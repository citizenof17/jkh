package com.jkh.BE.steps;

import com.jkh.BE.clients.AuthClient;
import com.jkh.BE.models.RegisterBadResponse;
import com.jkh.BE.models.RegisterRequest;
import com.jkh.BE.models.RegisterResponse;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

import static com.jkh.utils.AllureUtils.saveText;
import static com.jkh.utils.Assertions.compareIfEqual;
import static com.jkh.utils.ObjMapper.body2Object;
import static com.jkh.utils.ObjMapper.objest2Json;
import static com.jkh.utils.TestConstants.R;


@Component
public class AuthSteps {

    private final AuthClient authClient;
    private String sessionId;
    private RegisterResponse registerResponse;
    private RegisterBadResponse registerBadResponse;

    @Autowired
    public AuthSteps(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Step("Register new user")
    public void registerUser(RegisterRequest request) throws Exception {
        registerResponse = getDataAndSessionID(authClient.register(request), RegisterResponse.class);
        saveText(R, objest2Json(registerResponse));
    }

    @Step("Check response")
    public void checkRegisterResponse(RegisterResponse expectedResponse) {
        compareIfEqual(registerResponse, expectedResponse);
    }

    @Step("Register incorrect new user")
    public void registerIncorrectUser(RegisterRequest request) throws Exception {
        registerBadResponse = body2Object(authClient.register(request), RegisterBadResponse.class);
        saveText(R, objest2Json(registerBadResponse));
    }

    @Step("Check incorrect response")
    public void checkIncorrectRegisterResponse(RegisterBadResponse expectedResponse) {
        compareIfEqual(registerBadResponse, expectedResponse);
    }

    private <T> T getDataAndSessionID(Response response, Class<T> clazz) throws IOException {
        sessionId = response.headers().get("set-cookie").toString().split(";")[0].substring(1);
        return body2Object(response, clazz);
    }
}
