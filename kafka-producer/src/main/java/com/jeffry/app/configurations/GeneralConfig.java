package com.jeffry.app.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "app", ignoreInvalidFields = false)
public class GeneralConfig {
    private String topic;
}
