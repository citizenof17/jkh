package com.jkh.BE.steps;

import com.jkh.BE.clients.AuthClient;
import com.jkh.BE.models.*;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

import static com.jkh.utils.AllureUtils.saveText;
import static com.jkh.utils.Assertions.compareIfEqual;
import static com.jkh.utils.ObjMapper.*;
import static com.jkh.utils.TestConstants.R;


@Component
public class AuthSteps {

    private final AuthClient authClient;
    private String sessionId;
    private RegisterResponse registerResponse;
    private RegisterBadResponse registerBadResponse;
    private LoginResponse loginResponse;

    @Autowired
    public AuthSteps(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Step("Signing in with correct credential")
    public void loginCorrectUser(LoginRequest credential) throws IOException {
        loginResponse = getDataAndSessionID(authClient.login(credential), LoginResponse.class);
        //saveText(R, object2Json(loginResponse));
    }

    @Step("Signing in with incorrect credential")
    public void loginIncorrectUser(LoginRequest credential) throws IOException {
        loginResponse = getDataAndSessionID(authClient.login(credential), LoginResponse.class);
    }

    @Step("Check register response")
    public void checkLoginResponse(LoginResponse expectedResponse) {
        compareIfEqual(loginResponse, expectedResponse);
    }

    @Step("Register new user")
    public void registerUser(RegisterRequest request) throws Exception {
        registerResponse = getDataAndSessionID(authClient.register(request), RegisterResponse.class);
        //saveText(R, object2Json(registerResponse));
    }

    @Step("Check register response")
    public void checkRegisterResponse(RegisterResponse expectedResponse) {
        compareIfEqual(registerResponse, expectedResponse);
    }

    @Step("Register incorrect register new user")
    public void registerIncorrectUser(RegisterRequest request) throws Exception {
        registerBadResponse = body2Object(authClient.register(request), RegisterBadResponse.class);
        saveText(R, object2Json(registerBadResponse));
    }

    @Step("Check incorrect response")
    public void checkIncorrectRegisterResponse(RegisterBadResponse expectedResponse) {
        compareIfEqual(registerBadResponse, expectedResponse);
    }

    private <T> T getDataAndSessionID(Response response, Class<T> clazz) throws IOException {
        sessionId = response.headers().get("set-cookie").toString().split(";")[0].substring(1);
        return body2Object(response, clazz);
    }

    public String getSessionId() {
        return sessionId;
    }
}
