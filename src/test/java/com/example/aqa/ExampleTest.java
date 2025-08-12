package com.example.aqa;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Example test demonstrating interactions with the mock application and server.
 * <p>
 * It showcases the typical flow for UI driven tests: waiting for elements,
 * performing actions and verifying results with a {@link org.springframework.retry.support.RetryTemplate}.
 */
@Slf4j
class ExampleTest extends BaseTest {

    /**
     * Simple test that clicks a button and verifies the label text using retries.
     */
    @Test
    @DisplayName("Example test")
    void exampleTest() {

        log.info("Waiting for button...");
        pageExample.someButton().waitObject();

        log.info("Clicking on the button...");
        pageExample.someButton().click();

        assertionRetry.execute(context -> {
            log.info("Getting text from the label...");
            var text = pageExample.someLabel().getText();

            log.info("Checking if text is correct...");
            return Assertions.assertThat(text).isEqualTo("Mock text value");
        });
    }
}
