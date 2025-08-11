package com.example.aqa.configuration;

import com.example.aqa.configuration.extesion.ExtensionConfiguration;
import com.example.aqa.configuration.rest.RestApiClientConfiguration;
import com.example.aqa.configuration.driver.appium.AppiumConfiguration;
import com.example.aqa.driver.AppDriver;
import com.example.aqa.driver.MockAppDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Entry point Spring configuration assembling all framework pieces.
 */
@Configuration
@ComponentScan(basePackages = "com.example.aqa")
@Import({
        AppiumConfiguration.class,
        RestApiClientConfiguration.class,
        ExtensionConfiguration.class
})
public class MainConfiguration {

    /**
     * Provides the {@link AppDriver} used in tests.
     *
     * @return mock implementation of the application driver
     */
    @Bean
    public AppDriver appDriver() {
        return new MockAppDriver();
    }

}
