package com.example.aqa;

import com.example.aqa.app.client.example.ElementsPage;
import com.example.aqa.app.server.model.GitHubUser;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Demonstrates interaction with {@link ElementsPage} and nested page objects.
 */
@Slf4j
class ExampleTest extends BaseTest {

    /** GitHub username to fetch user information. */
    private static final String USERNAME = "anikolaevskiy";

    /** Page object providing access to screen elements. */
    @Autowired
    private ElementsPage elementsPage;

    private GitHubUser user;

    /**
     * Initializes the test by fetching user information from the GitHub API.
     * This runs before each test method to ensure the user data is fresh.
     */
    @BeforeEach
    public void setUp(){
        log.info("Getting user information by GitHub API...");
        user = restApiClient.getUser(USERNAME);
    }

    /**
     * End-to-end example verifying visibility and interactions on the elements page.
     */
    @Test
    @DisplayName("Example test")
    void exampleTest() {

        log.info("Checking if search input is displayed...");
        Assertions.assertThat(elementsPage.searchInput().isDisplayed())
                .as("Search input should be displayed")
                .isTrue();

        log.info("Sending text to search input...");
        elementsPage.searchInput().sendText("anikolaevskiy");

        log.info("Waiting for button...");
        elementsPage.searchButton().waitObject();

        log.info("Checking if search button is displayed and has correct text...");
        Assertions.assertThat(elementsPage.searchButton().isDisplayed())
                .as("Search button should be displayed")
                .isTrue();

        Assertions.assertThat(elementsPage.searchButton().getText())
                .as("Search button text should be 'Search'")
                .isEqualTo("Search");

        log.info("Clicking on the search button...");
        elementsPage.searchButton().click();

        log.info("Waiting for user card to be displayed...");
        elementsPage.userCard().waitObject();
        elementsPage.userCard().avatar().waitObject();

        //For example
        assertionRetry.execute(context -> {
            log.info("Checking user repository count...");
            return Assertions.assertThat(Integer.parseInt(elementsPage.userCard().publicReposNumber().getText().replaceAll(" ", "")))
                    .as("Public repos number should be greater than or equal to 1")
                    .isEqualTo(user.publicRepos());
        });
    }
}
