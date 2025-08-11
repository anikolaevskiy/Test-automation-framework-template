package com.example.aqa;

import com.example.aqa.app.client.PageExample;
import com.example.aqa.app.server.RestApiClient;
import com.example.aqa.configuration.MainConfiguration;
import com.example.aqa.junit.extesion.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;

@SpringBootTest(classes = MainConfiguration.class)
public class BaseTest {

    @Autowired
    @RegisterExtension
    protected ScreenshotOnFailureExtension screenshotOnFailureExtension;

    @Autowired
    protected PageExample pageExample;

    @Autowired
    protected RestApiClient restApiClient;

    @Autowired
    protected RetryTemplate assertionRetry;
}
