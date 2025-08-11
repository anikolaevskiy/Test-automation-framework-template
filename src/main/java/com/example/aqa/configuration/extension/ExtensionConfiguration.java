package com.example.aqa.configuration.extension;

import com.example.aqa.junit.extension.ScreenshotOnFailureExtension;
import io.appium.java_client.AppiumDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration that registers JUnit extensions as beans.
 */
@Configuration
public class ExtensionConfiguration {

    /**
     * Provides an extension that captures screenshots upon test failures.
     *
     * @return instance of {@link ScreenshotOnFailureExtension}
     */
    @Bean
    public ScreenshotOnFailureExtension screenshotOnFailureExtension() {
        return new ScreenshotOnFailureExtension();
    }
}
