package com.magambell.server.common.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of(
                "http://localhost:3000",
                "http://localhost:5500",
                "http://localhost:8080",
                "http://127.0.0.1:3000",
                "http://127.0.0.1:5500",
                "https://localhost:3000",
                "https://192.168.219.102:3000",
                "http://127.0.0.1:8080",
                "https://www.mgbell-server.run",
                "http://192.168.219.103:3000",
                "https://magambell-dev.vercel.app",
                "https://magambell-test.vercel.app",
                "https://magambell.com"
        ));

        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setExposedHeaders(List.of("Authorization", "RefreshToken"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
