package com.example.aqa.configuration.driver.playwright;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties for configuring a Playwright browser connection.
 * <p>
 * Externalising Playwright specific settings keeps the framework flexible and
 * allows contributors to provide their own <code>playwright.properties</code>
 * without touching source code.
 */
@PropertySource("classpath:playwright.properties")
@ConfigurationProperties(prefix = "playwright")
@ConfigurationPropertiesScan
public record PlaywrightProperties(String appHost, String gridHost) {
}

