package com.example.aqa.configuration.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:server.properties")
@ConfigurationProperties(prefix = "server")
@ConfigurationPropertiesScan
@Data
public class ServerProperties {

    private String host;
    private int port;
}
