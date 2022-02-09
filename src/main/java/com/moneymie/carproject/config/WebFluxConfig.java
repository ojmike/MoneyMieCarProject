package com.moneymie.carproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class WebFluxConfig {

    @Bean
    public WebClient getWebClient() {

        return WebClient.builder()
                .baseUrl("https://raw.githubusercontent.com/primefaces/primeui/master/showcase/resources/data")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "https://raw.githubusercontent.com/primefaces/primeui/master/showcase/resources/data"))
                .build();
    }
}
