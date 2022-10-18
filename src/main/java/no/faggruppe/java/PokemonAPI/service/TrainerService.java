package no.faggruppe.java.PokemonAPI.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.consumer.PokeAPIConsumer;
import no.faggruppe.java.PokemonAPI.consumer.PokeDBConsumer;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerRequestPokeDb;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerResponse;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerService {
    private final PokeDBConsumer pokeDBConsumer;
    private final PokeAPIConsumer pokeAPIConsumer;

    public CreateTrainerResponse createTrainer(String trainerName, String[] activePokemonNames, String[] storagePokemonNames) {
        val activePokemon = Stream.of(activePokemonNames)
                .map(pokeAPIConsumer::getPokemonFromName).toArray(Pokemon[]::new);
        val storagePokemon = Stream.of(storagePokemonNames)
                .map(pokeAPIConsumer::getPokemonFromName).toArray(Pokemon[]::new);
        val createTrainerRequest = CreateTrainerRequestPokeDb.builder()
                .trainerName(trainerName)
                .pokemonActive(activePokemon)
                .pokemonStorage(storagePokemon).build();
        val createdTrainer = pokeDBConsumer.createTrainer(createTrainerRequest);
        log.info(createdTrainer.message());
        return createdTrainer;
    }
}
