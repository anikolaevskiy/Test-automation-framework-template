package com.example.aqa.app.server;

import com.example.aqa.app.server.model.Something;
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

    /**
     * Retrieves a {@link Something} from the server.
     *
     * @return entity returned by the service
     */
    Something getSomething();

    /**
     * Creates a {@link Something} entity on the server.
     *
     * @param stringValue string value
     * @param intValue numeric value
     * @return created entity
     */
    Something createSomething(String stringValue, int intValue);
}
