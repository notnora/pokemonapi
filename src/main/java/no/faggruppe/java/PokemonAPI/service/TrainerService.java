package no.faggruppe.java.PokemonAPI.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.consumer.PokeAPIConsumer;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Trainer.TrainerResponse;
import no.faggruppe.java.PokemonAPI.repository.TrainerEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerService {
    private final PokeAPIConsumer pokeAPIConsumer;

    private final PokemonStorageRepositoryService pokemonStorageRepositoryService;
    private final TrainerRepositoryService trainerRepositoryService;

    public TrainerResponse createTrainer(String trainerName, String[] partyPokemonNames, String[] storagePokemonNames) {
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

        return TrainerResponse.builder()
                .trainerName(trainerName)
                .partyPokemon(partyPokemon.toArray(Pokemon[]::new))
                .storagePokemon(storagePokemon.toArray(Pokemon[]::new))
                .message(String.format("Created trainer '%s' successfully", trainerName))
                .build();
    }

    public TrainerResponse fetchTrainerDetails(String trainerName) {
        val partyPokemon = pokemonStorageRepositoryService.getAllPokemonByTrainer(trainerName, true);
        val storagePokemon = pokemonStorageRepositoryService.getAllPokemonByTrainer(trainerName, false);
        return TrainerResponse.builder()
                .trainerName(trainerName)
                .partyPokemon(partyPokemon.toArray(Pokemon[]::new))
                .storagePokemon(storagePokemon.toArray(Pokemon[]::new))
                .message(String.format("Fetched trainer '%s' successfully", trainerName))
                .build();
    }

    public TrainerResponse[] fetchAllTrainersDetails() {
        val trainers = trainerRepositoryService.fetchAllTrainers();
        List<TrainerEntity> trainersList = new ArrayList<TrainerEntity>();
        trainers.iterator().forEachRemaining(trainersList::add);
        val trainerDetails = trainersList.stream()
                .map(trainerEntity -> fetchTrainerDetails(trainerEntity.getId()));
        return trainerDetails.toArray(TrainerResponse[]::new);
    }
}
