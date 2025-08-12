package com.example.aqa.configuration.driver.appium;

import lombok.Data;
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
@Data
public class AppiumProperties {

    /** Appium server host. */
    private String host;

    /** Appium server port. */
    private int port;

    /** Device name for the session. */
    private String device;

    /** Path to the application under test. */
    private String app;

    /** Command timeout in minutes. */
    private int timeOut;

}
