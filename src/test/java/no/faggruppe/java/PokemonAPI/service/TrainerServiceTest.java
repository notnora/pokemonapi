package no.faggruppe.java.PokemonAPI.service;

import lombok.val;
import no.faggruppe.java.PokemonAPI.consumer.PokeAPIConsumer;
import no.faggruppe.java.PokemonAPI.consumer.PokeDBConsumer;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerRequestPokeDb;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class) // Burker Mockito extension med Junit
class TrainerServiceTest {
    @Mock
    // Mocker vekk consumeren, fordi vi vil bare teste servicen.
    // Under testen vil consumeren bare være et dummy-objekt.
    // Uten mockene, vil det komme nullpointerexception når servicen prøver å bruke den.
    private PokeDBConsumer pokeDBConsumer;
    @Mock
    private PokeAPIConsumer pokeAPIConsumer;

    @InjectMocks
    // Mocken over skal brukes (injectes) inn i TrainerService.
    private TrainerService trainerService;

    @Test
    @DisplayName("Oppretter en trener med navn, 1 active pokemon og 1 storage pokemon - suksess")
    void createTrainerWithNameOneActiveOneStoragePokemonSuccessfully() {
        // Opprettelse av "test-data"
        val trainerName = "TestTrainer";
        val pokemonBulba = Pokemon.builder().name("bulbasaur").build();
        val pokemonSquirtle = Pokemon.builder().name("squirtle").build();
        val pokemonActive = new String[]{"bulbasaur"};
        val pokemonStorage = new String[]{"squirtle"};
        val createTrainerResponse  = CreateTrainerResponse.builder()
                .trainerName(trainerName)
                .pokemonActive(new Pokemon[]{pokemonBulba})
                .pokemonStorage(new Pokemon[]{pokemonSquirtle})
                .message("Trainer created!")
                .build();
        /*
        Om doReturn().when()
            * Må gjøres før test-kallet (trainerService.createTrainer)
            * Parameter til metoden som blir stubbet kan være 'any' eller egendefinert.
        // Egendefinert parameter:
        val createTrainerRequestPokeDb = CreateTrainerRequestPokeDb.builder()
                .trainerName(trainerName)
                .pokemonActive(new Pokemon[]{pokemonBulba})
                .pokemonStorage(new Pokemon[]{pokemonSquirtle})
                .build();
         */
        doReturn(createTrainerResponse).when(pokeDBConsumer).createTrainer(any(CreateTrainerRequestPokeDb.class));

        val trainer = trainerService.createTrainer(trainerName, pokemonActive, pokemonStorage);

        /*
        Selve testen:
            * Sjekker at trainer (actual) er det samme som createTrainerResponse (expected)
        */
        assertThat(trainer).isEqualTo(createTrainerResponse);
    }
}