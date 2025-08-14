package com.example.aqa.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Authentication token returned by the sample REST service.
 */
public class Token {

    /** Encoded access token string. */
    private String token;
}
