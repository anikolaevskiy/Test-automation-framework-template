package com.example.aqa.configuration.driver.waiter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;


@PropertySource(value = "classpath:common.properties")
@ConfigurationProperties(prefix = "wait")
@ConfigurationPropertiesScan
@Data
public class WebDriverWaitProperties {

    private int defaultDuration;

}
