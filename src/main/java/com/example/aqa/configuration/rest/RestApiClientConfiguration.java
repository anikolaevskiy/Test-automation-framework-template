package com.example.aqa.configuration.rest;

import com.example.aqa.app.server.RestApiClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the REST API client used in tests.
 * <p>
 * By creating the client as a Spring bean we avoid duplicating connection setup
 * across tests and can easily swap the implementation if needed.
 */
@Configuration
@EnableConfigurationProperties(ServerProperties.class)
public class RestApiClientConfiguration {

    /**
     * Creates a {@link RestApiClient} bean configured with server properties.
     * <p>
     * The server details are externalized in {@link ServerProperties} so the
     * same test code can target different environments simply by changing
     * configuration files.
     *
     * @param serverProperties server connection settings
     * @return configured REST client
     */
    @Bean
    public RestApiClient restApiClient(ServerProperties serverProperties) {
        return new RestApiClient(serverProperties.getHost(), serverProperties.getPort());
    }

}


