package com.jkh.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.jkh.utils.TestConstants.R;

public class DefaultLogger extends feign.Logger {

    private final Logger logger;

    public DefaultLogger(Class<?> clazz) {
        this.logger = (LoggerFactory.getLogger(clazz));
    }

    private String prettyResponse(Response response) {
        StringBuilder body = new StringBuilder(StringUtils.EMPTY);
        body.append(response.status()).append("\n");
        for (Map.Entry<String, Collection<String>> headers : response.headers().entrySet()) {
            body.append(headers.getKey()).append(StringUtils.SPACE);
            for (String value : headers.getValue()) {
                body.append(value).append(StringUtils.SPACE);
            }
            body.append("\n");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(response.body().toString(), Object.class);
            body.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (Exception ignored) {
        }
        return body.toString();
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (logger.isDebugEnabled()) {
            super.logRequest(configKey, logLevel, request);
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
                                              long elapsedTime) throws IOException {
        Response responseAfterLog = super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
        AllureUtils.saveText(R, prettyResponse(responseAfterLog));
        return responseAfterLog;
    }

    @Override
    protected void log(String configKey, String format, Object[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(methodTag(configKey) + format, args));
        }
    }
}
