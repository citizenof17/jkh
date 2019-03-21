package com.jkh;

import com.jkh.BE.ConfigurationBE;
import com.jkh.FE.ConfigurationFE;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = {"com.jkh.BE.steps",
        "com.jkh.FE.steps", "com.jkh.utils", "com.jkh.FE.pages"})
@Import({ConfigurationBE.class, ConfigurationFE.class})
public class ConfigurationMain {
}
