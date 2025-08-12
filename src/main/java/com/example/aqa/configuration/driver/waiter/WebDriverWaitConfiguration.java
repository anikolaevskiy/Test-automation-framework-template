package com.example.aqa.configuration.driver.waiter;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(WebDriverWaitProperties.class)
public class WebDriverWaitConfiguration {

    @Bean
    public WebDriverWait webDriverWait(WebDriverWaitProperties properties) {
        return new WebDriverWait(null, Duration.ofSeconds(properties.getDefaultDuration()));
    }
}
