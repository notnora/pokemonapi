package no.faggruppe.java.PokemonAPI.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerRequestPokeDb;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
@RequiredArgsConstructor
public class PokeDBConsumer {
    private final WebClient webClient;

    public CreateTrainerResponse createTrainer(CreateTrainerRequestPokeDb requestBody) {
        val createdTrainer = webClient.put()
                .uri(UriComponentsBuilder.fromHttpUrl("https://if0ju57ov0.execute-api.us-east-1.amazonaws.com/trainer")
                        .build().toUri())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(CreateTrainerResponse.class)
                .block();
        return createdTrainer;
    };
}