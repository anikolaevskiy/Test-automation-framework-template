package com.example.aqa.app.server;

import com.example.aqa.app.server.model.Something;
import com.example.aqa.app.server.model.Token;
import com.example.aqa.app.server.model.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

/**
 * Simple REST client used for demonstrating API interactions with {@link RestAssured}.
 * <p>
 * The client intentionally configures base URI and logging globally so that
 * individual tests remain concise. It showcases how service clients can be
 * registered as Spring beans and reused across tests.
 */
public class RestApiClient {

    /**
     * Creates a client and configures RestAssured base URI and logging filters.
     * <p>
     * Centralising this setup keeps tests free from repetitive configuration and
     * ensures every request/response is logged for easier debugging.
     *
     * @param host API host
     * @param port API port
     */
    public RestApiClient(String host, Integer port) {

        if (port != null) {
            RestAssured.baseURI = String.format("%s:%d", host, port);
        } else {
            RestAssured.baseURI = String.format(host);
        }
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


    public Token auth(String username, String password) {
        return RestAssured.given()
                .body(new User(username, password))
                .when()
                .contentType("application/json")
                .post("/auth")
                .then()
                .extract()
                .response()
                .as(Token.class);
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
     * @param intValue    integer value to set
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



