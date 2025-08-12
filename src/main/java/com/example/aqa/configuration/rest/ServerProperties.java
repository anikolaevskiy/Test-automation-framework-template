package com.example.aqa.configuration.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties describing the target server used by REST tests.
 * <p>
 * Externalising host and port allows the same tests to run against local
 * mocks, staging or production-like environments simply by changing the
 * property file.
 */
@PropertySource(value = "classpath:server.properties")
@ConfigurationProperties(prefix = "server")
@ConfigurationPropertiesScan
@Data
public class ServerProperties {

    /** Server host address. */
    private String host;

    /** Server port number. */
    private int port;
}
