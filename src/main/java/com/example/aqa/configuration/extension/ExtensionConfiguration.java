package com.example.aqa.configuration.extension;

import com.example.aqa.junit.extension.ScreenshotOnFailureExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration that registers JUnit extensions as beans.
 * <p>
 * Declaring extensions as beans allows them to participate in dependency
 * injection and be shared across tests, keeping setup logic in one place.
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
