package com.example.aqa;

import com.example.aqa.app.server.client.RestApiClient;
import com.example.aqa.configuration.MainConfiguration;
import com.example.aqa.junit.extension.ScreenshotOnFailureExtension;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;

/**
 * Base class for tests providing common Spring context and utilities.
 * <p>
 * Centralising the Spring Boot setup in a superclass keeps individual tests
 * focused on behaviour while still giving them access to shared beans such as
 * page objects, REST clients and retry logic.
 */
@ExtendWith(AllureJunit5.class)
@SpringBootTest(classes = MainConfiguration.class)
public class BaseTest {

    /** Extension that logs mock screenshots when a test fails. */
    @Autowired
    @RegisterExtension
    protected ScreenshotOnFailureExtension screenshotOnFailureExtension;

    /** REST API client for server interactions. */
    @Autowired
    protected RestApiClient restApiClient;

    /** Retry template for repeating assertions. */
    @Autowired
    protected RetryTemplate assertionRetry;
}
