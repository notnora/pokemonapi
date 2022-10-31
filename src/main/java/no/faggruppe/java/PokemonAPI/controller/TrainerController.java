package no.faggruppe.java.PokemonAPI.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerRequest;
import no.faggruppe.java.PokemonAPI.dto.Trainer.TrainerResponse;
import no.faggruppe.java.PokemonAPI.service.TrainerService;
import org.springframework.web.bind.annotation.PathVariable;
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
    public TrainerResponse createTrainer (@RequestBody CreateTrainerRequest body) {
        return trainerService.createTrainer(body.trainerName(), body.pokemonActive(), body.pokemonStorage());
    }
    @RequestMapping("/{trainerName}")
    public TrainerResponse fetchTrainerDetails(@PathVariable("trainerName") String trainerName) {
        return trainerService.fetchTrainerDetails(trainerName);
    }

    @RequestMapping("/all")
    public TrainerResponse[] fetchAllTrainersDetails() {
        return trainerService.fetchAllTrainersDetails();
    }
}
