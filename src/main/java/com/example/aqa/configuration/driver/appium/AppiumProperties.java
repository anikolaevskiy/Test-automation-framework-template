package com.example.aqa.configuration.driver.appium;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties for configuring an Appium server connection.
 * <p>
 * Using a dedicated properties object keeps environment specific values out of
 * the code base. This makes the template easy to share between teams and allows
 * different contributors to supply their own <code>appium.properties</code>
 * without modifying source files.
 */
@PropertySource(value = "classpath:appium.properties")
@ConfigurationProperties(prefix = "appium")
@ConfigurationPropertiesScan
public record AppiumProperties(String host, String device, String app, int timeOut) {
}
