package no.faggruppe.java.PokemonAPI.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.consumer.PokeAPIConsumer;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerResponse;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerService {
    private final PokeAPIConsumer pokeAPIConsumer;

    private final PokemonStorageRepositoryService pokemonStorageRepositoryService;
    private final TrainerRepositoryService trainerRepositoryService;

    public CreateTrainerResponse createTrainer(String trainerName, String[] partyPokemonNames, String[] storagePokemonNames) {
        val partyPokemon = Stream.of(partyPokemonNames)
                .map(pokeAPIConsumer::getPokemonFromName).toList();
        val storagePokemon = Stream.of(storagePokemonNames)
                .map(pokeAPIConsumer::getPokemonFromName).toList();
        trainerRepositoryService.createTrainer(trainerName);
        log.info("Trainer with name and id '{}' created", trainerName);

        pokemonStorageRepositoryService.saveAllStoragePokemon(partyPokemon, trainerName,true);
        log.info("Saved all party pokemons in db");
        pokemonStorageRepositoryService.saveAllStoragePokemon(storagePokemon, trainerName,false);
        log.info("Saved all storage pokemons in db");

        return CreateTrainerResponse.builder()
                .trainerName(trainerName)
                .partyPokemon(partyPokemon.toArray(Pokemon[]::new))
                .storagePokemon(storagePokemon.toArray(Pokemon[]::new))
                .message(String.format("Created trainer '%s' successfully", trainerName))
                .build();
    }

    public CreateTrainerResponse fetchTrainerDetails(String trainerName) {
        val partyPokemon = pokemonStorageRepositoryService.getAllPokemonByTrainer(trainerName, true);
        val storagePokemon = pokemonStorageRepositoryService.getAllPokemonByTrainer(trainerName, false);
        return CreateTrainerResponse.builder()
                .trainerName(trainerName)
                .partyPokemon(partyPokemon.toArray(Pokemon[]::new))
                .storagePokemon(storagePokemon.toArray(Pokemon[]::new))
                .message(String.format("Fetched trainer '%s' successfully", trainerName))
                .build();
    }
 /*   public CreateTrainerResponse createTrainerAws(String trainerName, String[] activePokemonNames, String[] storagePokemonNames) {
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
        return createdTrainer;*/
    //}
}
