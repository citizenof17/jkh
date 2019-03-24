package com.jkh.BE.clients;

import com.jkh.BE.models.LoginRequest;
import com.jkh.BE.models.RegisterRequest;
import feign.Headers;
import feign.RequestLine;
import feign.Response;

public interface AuthClient {

    @RequestLine("POST /register")
    @Headers("Content-Type: application/json")
    Response register(RegisterRequest body);

    @RequestLine("POST /login")
    @Headers("Content-Type: application/json")
    Response login(LoginRequest body);
}
