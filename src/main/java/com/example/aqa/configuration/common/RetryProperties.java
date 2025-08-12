package com.example.aqa.configuration.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration properties for retry behavior used across tests.
 * <p>
 * Exposing retry settings as properties allows each environment to control how
 * aggressive the polling should be without modifying test code.
 */
@PropertySource(value = "classpath:common.properties")
@ConfigurationProperties(prefix = "retry")
@ConfigurationPropertiesScan
@Data
public class RetryProperties {

    /** Number of retry attempts. */
    private int attempts;

    /** Backoff delay between attempts. */
    private int backoff;

}
