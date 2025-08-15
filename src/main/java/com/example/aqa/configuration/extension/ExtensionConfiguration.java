package com.example.aqa.configuration.extension;

import com.example.aqa.configuration.common.CommonConfiguration;
import com.example.aqa.junit.extension.ScreenshotOnFailureExtension;
import com.example.aqa.tools.Screenshot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration that registers JUnit extensions as beans.
 * <p>
 * Declaring extensions as beans allows them to participate in dependency
 * injection and be shared across tests, keeping setup logic in one place.
 */
@Configuration
@Import(CommonConfiguration.class)
public class ExtensionConfiguration {

    /**
     * Provides an extension that captures screenshots upon test failures.
     *
     * @return instance of {@link ScreenshotOnFailureExtension}
     */

    @Bean
    public ScreenshotOnFailureExtension screenshot(Screenshot screenshot) {
        return new ScreenshotOnFailureExtension(screenshot);
    }
}
