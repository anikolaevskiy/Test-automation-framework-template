package com.example.aqa.configuration.rest;

import com.example.aqa.app.server.RestApiClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the REST API client used in tests.
 */
@Configuration
@EnableConfigurationProperties(ServerProperties.class)
public class RestApiClientConfiguration {

    /**
     * Creates a {@link RestApiClient} bean configured with server properties.
     *
     * @param serverProperties server connection settings
     * @return configured REST client
     */
    @Bean
    public RestApiClient restApiClient(ServerProperties serverProperties) {
        return new RestApiClient(serverProperties.getHost(), serverProperties.getPort());
    }

}


