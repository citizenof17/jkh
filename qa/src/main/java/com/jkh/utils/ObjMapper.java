package com.jkh.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;

import java.io.IOException;

public class ObjMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T body2Object(Response response, Class<T> currentClass) throws IOException {
        return objectMapper.readValue(Util.toString(response.body().asReader()), currentClass);
    }

    public static <T> String object2Json(T object) throws IOException {
        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}
