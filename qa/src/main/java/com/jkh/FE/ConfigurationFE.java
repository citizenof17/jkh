package com.jkh.FE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationFE {

    @Value("${currentBrowser:firefox}")
    private String currentBrowser;

    @Value("${DEFAULT_PERIOD_BETWEEN_COUNTERS_SENDINGS:30}")
    private Integer n;

    @Value("${ADMIN_LOGIN:Administrator}")
    private String login;

    @Value("${ADMIN_PASSWORD:Administrator_1}")
    private String password;

    @Value("${ADMIN_NAME:Администратор Великий Ужаснович}")
    private String name;

    @Value("${ADMIN_EMAIL:Administrator@jkh.ru}")
    private String email;

    @Value("${ADMIN_PHONE:+79995550000}")
    private String phone;
}
