package com.example.aqa.configuration.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:common.properties")
@ConfigurationProperties(prefix = "retry")
@ConfigurationPropertiesScan
@Data
public class RetryProperties {

    private int attempts;
    private int backoff;

}
