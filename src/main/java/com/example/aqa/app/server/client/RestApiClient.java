package com.example.aqa.app.server.client;

import com.example.aqa.app.server.model.GitHubUser;

/**
 * Common REST API client contract used by tests.
 * Implementations can use different HTTP stacks such as RestAssured or Feign.
 */
public interface RestApiClient {

    /**
     * Retrieves information about a GitHub user.
     *
     * @param username GitHub username to get information about
     * @return GitHub user information
     */
    GitHubUser getUser(String username);
}
