package com.example.aqa.configuration.driver.selenium;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties for configuring a Selenium server connection.
 * <p>
 * Externalising Selenium specific settings keeps the framework flexible and
 * allows contributors to provide their own <code>selenium.properties</code>
 * without touching source code.
 */
@PropertySource("classpath:selenium.properties")
@ConfigurationProperties(prefix = "selenium")
@ConfigurationPropertiesScan
public record SeleniumProperties(String appHost, String gridHost) {
}
