package com.example.aqa.configuration.driver.waiter;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Spring configuration for constructing {@link WebDriverWait} instances.
 * <p>
 * The timeout duration is externalised in {@link WebDriverWaitProperties} so it
 * can be tuned without modifying source code.
 */
@Configuration
@EnableConfigurationProperties(WebDriverWaitProperties.class)
public class WebDriverWaitConfiguration {

    /**
     * Creates a {@link WebDriverWait} using the default duration from
     * properties.
     *
     * @param properties wait configuration properties
     * @return configured WebDriverWait
     */
    @Bean
    public WebDriverWait webDriverWait(WebDriverWaitProperties properties) {
        return new WebDriverWait(null, Duration.ofSeconds(properties.getDefaultDuration()));
    }
}
