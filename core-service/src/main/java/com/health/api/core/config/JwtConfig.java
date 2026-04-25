package com.health.api.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    private Secret secret;
    private long expiration;

    @Data
    public static class Secret {
        private String key;
    }
}