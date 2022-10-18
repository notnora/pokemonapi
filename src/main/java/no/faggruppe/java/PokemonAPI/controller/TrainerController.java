package no.faggruppe.java.PokemonAPI.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerRequest;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerResponse;
import no.faggruppe.java.PokemonAPI.service.TrainerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;
    @RequestMapping("/")
    public CreateTrainerResponse createTrainer (@RequestBody CreateTrainerRequest body) {
        return trainerService.createTrainer(body.trainerName(), body.pokemonActive(), body.pokemonStorage());
    }
}
