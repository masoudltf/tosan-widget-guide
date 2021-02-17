package com.tosan.modern.widget.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${management.web.cors.allowDomains}")
    private String corsDomains;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedOrigins(corsDomains)
                .allowedMethods(HttpMethod.GET.toString(),HttpMethod.HEAD.toString())
                .allowCredentials(true);
    }

}
