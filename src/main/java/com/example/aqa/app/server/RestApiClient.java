package com.example.aqa.app.server;

import com.example.aqa.app.server.model.Something;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class RestApiClient {

    public RestApiClient(String host, int port) {
        RestAssured.baseURI = String.format("%s:%d", host, port);
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Something getSomething() {
        return RestAssured.given()
                        .when()
                        .get("/something")
                        .then()
                        .extract()
                        .response()
                        .as(Something.class);
    }

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



