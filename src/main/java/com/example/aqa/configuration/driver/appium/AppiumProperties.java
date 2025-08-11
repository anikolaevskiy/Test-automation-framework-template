package com.example.aqa.configuration.driver.appium;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:appium.properties")
@ConfigurationProperties(prefix = "appium")
@ConfigurationPropertiesScan
@Data
public class AppiumProperties {

    private String host;
    private int port;
    private String device;
    private String app;
    private int timeOut;

}
