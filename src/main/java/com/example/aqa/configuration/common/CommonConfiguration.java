package com.example.aqa.configuration.common;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

/**
 * Common framework configuration such as retry templates.
 */
@Configuration
@EnableConfigurationProperties(RetryProperties.class)
public class CommonConfiguration {

    /**
     * Creates a {@link RetryTemplate} used for repeating assertions.
     *
     * @param properties retry configuration properties
     * @return configured retry template
     */
    @Bean
    public RetryTemplate assertionRetry(RetryProperties properties) {
        return RetryTemplate.builder().retryOn(AssertionError.class)
                .maxAttempts(properties.getAttempts())
                .fixedBackoff(properties.getAttempts())
                .build();
    }
}