package com.jkh.FE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationFE {

    @Value("${currentBrowser:firefox}")
    private String currentBrowser;
}
