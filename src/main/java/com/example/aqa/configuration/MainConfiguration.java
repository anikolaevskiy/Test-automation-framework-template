package com.example.aqa.configuration;

import com.example.aqa.configuration.extension.ExtensionConfiguration;
import com.example.aqa.configuration.rest.RestApiClientConfiguration;
import com.example.aqa.configuration.driver.appium.AppiumConfiguration;
import com.example.aqa.driver.AppDriver;
import com.example.aqa.driver.AppiumBasedAppDriver;
import com.example.aqa.driver.MockAppDriver;
import io.appium.java_client.AppiumDriver;
import org.springframework.context.annotation.*;

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
    @Profile("mock")
    public AppDriver mockAppDriver() {
        return new MockAppDriver();
    }

    /**
     * Provides the {@link AppDriver} used in tests when running with Appium.
     *
     * @param appiumDriver the Appium driver instance
     * @return Appium-based implementation of the application driver
     */
    @Bean
    @Profile("appium")
    public AppDriver appiumBasedAppDriver(AppiumDriver appiumDriver) {
        return new AppiumBasedAppDriver(appiumDriver);
    }

}
