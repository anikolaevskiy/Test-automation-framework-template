package com.example.aqa.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Credentials used for authentication requests.
 */
public class User {

    /** Login user name. */
    private String username;

    /** Plain text password. */
    private String password;
}
