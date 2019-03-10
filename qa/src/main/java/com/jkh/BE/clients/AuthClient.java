package com.jkh.BE.clients;

import com.jkh.BE.models.RegisterRequest;
import feign.Headers;
import feign.RequestLine;
import feign.Response;

public interface AuthClient {

    @RequestLine("POST /register")
    @Headers("Content-Type: application/json")
    Response register(RegisterRequest body);
}
