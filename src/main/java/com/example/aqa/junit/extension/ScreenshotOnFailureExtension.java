package com.example.aqa.junit.extension;

import com.example.aqa.tools.Screenshot;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Example of JUnit 5 extension that takes a screenshot on test failure.
 * <p>
 * The implementation only logs the intention to capture a screenshot. In a
 * real project this is the place to hook into WebDriver or Appium and persist
 * the image for debugging failing tests.
 */
@Slf4j
@RequiredArgsConstructor
public class ScreenshotOnFailureExtension implements TestWatcher {

    private final Screenshot screenshot;

    /**
     * Logs and mocks taking a screenshot when the observed test fails.
     *
     * @param context context of the failed test
     * @param cause   throwable that caused the failure
     */
    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("Test '{}' failed with exception: {}", context.getDisplayName(), cause.getMessage());
        log.info("Taking screenshot for debugging...");
        Files.write(Path.of("target", context.getRequiredTestMethod().getName() + ".png"), screenshot.get());
    }
}
