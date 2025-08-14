package com.example.aqa.app.server.client;

import com.example.aqa.app.server.model.Token;

/**
 * Common REST API client contract used by tests.
 * Implementations can use different HTTP stacks such as RestAssured or Feign.
 */
public interface RestApiClient {

    /**
     * Performs authentication and returns an access token.
     *
     * @param username user name
     * @param password user password
     * @return authentication token
     */
    Token auth(String username, String password);
}
