package com.jkh.BE.clients;

import com.jkh.BE.models.IndicationRequest;
import com.jkh.BE.models.IndicationResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.util.List;

public interface IndicationClient {

    @RequestLine("POST /sendIndications")
    @Headers({"Content-Type: application/json", "Cookie: {sessionId}"})
    IndicationResponse sendIndications(List<IndicationRequest> indicationRequests, @Param("sessionId") String sessionId);
}
