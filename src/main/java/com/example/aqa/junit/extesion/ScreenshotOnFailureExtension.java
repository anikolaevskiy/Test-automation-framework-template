package com.example.aqa.junit.extesion;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

/**
 * Example of JUnit 5 extension that takes a screenshot on test failure.
 */
@Slf4j
@RequiredArgsConstructor
public class ScreenshotOnFailureExtension implements TestWatcher {

    /**
     * Logs and mocks taking a screenshot when the observed test fails.
     *
     * @param context context of the failed test
     * @param cause   throwable that caused the failure
     */
    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("Mock taking screenshot on test failure: {}", context.getRequiredTestMethod().getName());
//        var screenshot =
//        Files.write(Path.of("target", context.getRequiredTestMethod().getName() + ".png"), screenshot);
    }
}
