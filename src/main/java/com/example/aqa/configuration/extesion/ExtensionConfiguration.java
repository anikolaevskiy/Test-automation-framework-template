package com.example.aqa.configuration.extesion;

import com.example.aqa.junit.extesion.ScreenshotOnFailureExtension;
import io.appium.java_client.AppiumDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtensionConfiguration {

    @Bean
    public ScreenshotOnFailureExtension screenshotOnFailureExtension() {
        return new ScreenshotOnFailureExtension();
    }
}
