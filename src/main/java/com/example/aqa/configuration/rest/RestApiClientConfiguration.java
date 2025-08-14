package com.example.aqa.configuration.rest;

import com.example.aqa.app.server.client.FeignBasedRestApiClient;
import com.example.aqa.app.server.client.RestApiClient;
import com.example.aqa.app.server.client.feign.GitHubFeignClient;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Configuration for the REST API client used in tests.
 * <p>
 * By creating the client as a Spring bean we avoid duplicating connection setup
 * across tests and can easily swap the implementation if needed.
 */
@Configuration
@EnableFeignClients(clients = {GitHubFeignClient.class})
@PropertySource("classpath:server.properties")
@Import(FeignAutoConfiguration.class)
public class RestApiClientConfiguration {

    /**
     * Registers JSON message converters used by Feign clients.
     *
     * @return container with a Jackson converter
     */
    @Bean
    public HttpMessageConverters messageConverters() {
        return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Provides a {@link RestApiClient} backed by a Feign HTTP client.
     *
     * @param gitHubFeignClient Feign client for GitHub API
     * @return implementation used in tests
     */
    @Bean
    public RestApiClient restApiClient(GitHubFeignClient gitHubFeignClient) {
        return new FeignBasedRestApiClient(gitHubFeignClient);
    }
}


