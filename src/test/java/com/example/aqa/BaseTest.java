package com.example.aqa;

import com.example.aqa.app.client.PageExample;
import com.example.aqa.app.server.RestApiClient;
import com.example.aqa.configuration.MainConfiguration;
import com.example.aqa.junit.extesion.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;

/**
 * Base class for tests providing common Spring context and utilities.
 */
@SpringBootTest(classes = MainConfiguration.class)
public class BaseTest {

    /** Extension that logs mock screenshots when a test fails. */
    @Autowired
    @RegisterExtension
    protected ScreenshotOnFailureExtension screenshotOnFailureExtension;

    /** Example page object used in tests. */
    @Autowired
    protected PageExample pageExample;

    /** REST API client for server interactions. */
    @Autowired
    protected RestApiClient restApiClient;

    /** Retry template for repeating assertions. */
    @Autowired
    protected RetryTemplate assertionRetry;
}
