package com.example.aqa.app.server;

import com.example.aqa.app.server.model.Something;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

/**
 * Simple REST client used for demonstrating API interactions with {@link RestAssured}.
 */
public class RestApiClient {

    /**
     * Creates a client and configures RestAssured base URI and logging filters.
     *
     * @param host API host
     * @param port API port
     */
    public RestApiClient(String host, int port) {
        RestAssured.baseURI = String.format("%s:%d", host, port);
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    /**
     * Retrieves a {@link Something} entity from the server.
     *
     * @return object returned by the <code>/something</code> endpoint
     */
    public Something getSomething() {
        return RestAssured.given()
                        .when()
                        .get("/something")
                        .then()
                        .extract()
                        .response()
                        .as(Something.class);
    }

    /**
     * Sends a request to create a {@link Something} entity on the server.
     *
     * @param stringValue string value to set
     * @param intValue integer value to set
     * @return created object returned by the server
     */
    public Something createSomething(String stringValue, int intValue) {
        return RestAssured.given()
                        .body(new Something(stringValue, intValue))
                        .when()
                        .put("/something")
                        .then()
                        .extract()
                        .response()
                        .as(Something.class);
    }
}



