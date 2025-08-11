package com.example.aqa.configuration.rest;

import com.example.aqa.app.server.RestApiClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ServerProperties.class)
public class RestApiClientConfiguration {

    @Bean
    public RestApiClient restApiClient(ServerProperties serverProperties) {
        return new RestApiClient(serverProperties.getHost(), serverProperties.getPort());
    }

}


