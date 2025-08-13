package com.example.aqa.configuration.rest;

import com.example.aqa.app.server.FeignRestApiClient;
import com.example.aqa.app.server.RestApiClient;
import com.example.aqa.app.server.ServerFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration for the REST API client used in tests.
 * <p>
 * By creating the client as a Spring bean we avoid duplicating connection setup
 * across tests and can easily swap the implementation if needed.
 */
@Configuration
@EnableFeignClients(clients = ServerFeignClient.class)
@Import(FeignAutoConfiguration.class)
public class RestApiClientConfiguration {

    /**
     * Provides a {@link RestApiClient} backed by a Feign HTTP client.
     *
     * @param serverFeignClient Feign declaration of the server endpoints
     * @return implementation used in tests
     */
    @Bean
    public RestApiClient restApiClient(ServerFeignClient serverFeignClient) {
        return new FeignRestApiClient(serverFeignClient);
    }
}


