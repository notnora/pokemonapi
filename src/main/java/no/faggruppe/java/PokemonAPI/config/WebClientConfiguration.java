package no.faggruppe.java.PokemonAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    @Bean("webClient")
    public WebClient webClient() {
        return WebClient.create();
    }
}
