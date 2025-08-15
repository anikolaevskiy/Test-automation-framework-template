package com.example.aqa.configuration.common;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

/**
 * Common framework configuration such as retry templates.
 * <p>
 * Centralising cross‑cutting beans avoids repeating wiring in every test and
 * makes it easy to tune behaviour through properties.
 */
@Configuration
@EnableConfigurationProperties(RetryProperties.class)
public class CommonConfiguration {

    /**
     * Creates a {@link RetryTemplate} used for repeating assertions.
     * <p>
     * Retry logic is useful when the system under test exhibits eventual
     * consistency. Exposing the attempts and backoff via {@link RetryProperties}
     * allows the same tests to be tuned for different environments.
     *
     * @param properties retry configuration properties
     * @return configured retry template
     */
    @Bean
    public RetryTemplate assertionRetry(RetryProperties properties) {
        return RetryTemplate.builder().retryOn(AssertionError.class)
                .maxAttempts(properties.getAttempts())
                .fixedBackoff(properties.getBackoff())
                .build();
    }
}
