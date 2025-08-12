package com.example.aqa.configuration.driver.waiter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration properties for Selenium explicit wait durations.
 * <p>
 * Exposing the value allows each environment to tune how long the driver
 * should wait for elements without changing source code.
 */
@PropertySource(value = "classpath:common.properties")
@ConfigurationProperties(prefix = "wait")
@ConfigurationPropertiesScan
@Data
public class WebDriverWaitProperties {

    /** Default wait time in seconds. */
    private int defaultDuration;

}
