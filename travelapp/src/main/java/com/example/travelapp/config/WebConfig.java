package com.example.travelapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
             //   .allowedOrigins("http://localhost:5500") // Adjust according to your front-end setup
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}