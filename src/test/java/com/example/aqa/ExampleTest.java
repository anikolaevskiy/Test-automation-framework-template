package com.example.aqa;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class ExampleTest extends BaseTest {

    @Test
    @DisplayName("Sorting by Raids score works correctly")
    void raidsScoreSortingTest() {

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
