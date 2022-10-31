package no.faggruppe.java.PokemonAPI.service;

import lombok.val;
import no.faggruppe.java.PokemonAPI.consumer.PokeAPIConsumer;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Trainer.TrainerResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class) // Burker Mockito extension med Junit
class TrainerServiceTest {
    @Mock
    // Mocker vekk consumeren, fordi vi vil bare teste servicen.
    // Under testen vil consumeren bare være et dummy-objekt.
    // Uten mockene, vil det komme nullpointerexception når servicen prøver å bruke den.
    private PokeAPIConsumer pokeAPIConsumer;
    @Mock
    private PokemonStorageRepositoryService pokemonStorageRepositoryService;
    @Mock
    private TrainerRepositoryService trainerRepositoryService;

    @InjectMocks
    // Mockene over skal brukes (injectes) inn i TrainerService.
    private TrainerService trainerService;

    @Test
    @DisplayName("Oppretter en trener med navn, 1 active pokemon og 1 storage pokemon - suksess")
    void createTrainerWithNameOneActiveOneStoragePokemonSuccessfully() {
        // Opprettelse av "test-data"
        val trainerName = "TestTrainer";
        val pokemonBulba = Pokemon.builder()
                .name("bulbasaur")
                .trainerID(trainerName).build();
        val pokemonSquirtle = Pokemon.builder()
                .name("squirtle").
        trainerID(trainerName).build();
        val pokemonActive = new String[]{"bulbasaur"};
        val pokemonStorage = new String[]{"squirtle"};
        val createTrainerResponse  = TrainerResponse.builder()
                .trainerName(trainerName)
                .partyPokemon(new Pokemon[]{pokemonBulba})
                .storagePokemon(new Pokemon[]{pokemonSquirtle})
                .message(String.format("Created trainer '%s' successfully", trainerName))
                .build();
        /*
        Om doReturn().when()
            * Må gjøres før test-kallet (trainerService.createTrainer)
            * Parameter til metoden som blir stubbet kan være 'any' eller egendefinert.
        // med any parameter:
             * doReturn(pokemonBulba).when(pokeAPIConsumer).getPokemonFromName(any(String.class));
             * uansett hvilken streng som blir sendt inn, vil pokemonBulba bli returnert.
         */
        doReturn(pokemonBulba).when(pokeAPIConsumer).getPokemonFromName("bulbasaur");
        doReturn(pokemonSquirtle).when(pokeAPIConsumer).getPokemonFromName("squirtle");
        val trainer = trainerService.createTrainer(trainerName, pokemonActive, pokemonStorage);

        /*
        Selve testen:
            * Sjekker at trainer (actual) er det samme som createTrainerResponse (expected)
        */
        assertThat(trainer.trainerName()).isEqualTo(createTrainerResponse.trainerName());
        assertThat(trainer.partyPokemon()[0].name()).isEqualTo(pokemonBulba.name());
        assertThat(trainer.storagePokemon()[0].name()).isEqualTo(pokemonSquirtle.name());
        assertThat(trainer.partyPokemon()[0].trainerID()).isEqualTo(pokemonBulba.trainerID());
        assertThat(trainer.storagePokemon()[0].trainerID()).isEqualTo(pokemonSquirtle.trainerID());
    }
}