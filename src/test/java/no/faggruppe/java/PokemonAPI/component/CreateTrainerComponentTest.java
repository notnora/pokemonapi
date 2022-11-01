package no.faggruppe.java.PokemonAPI.component;

import lombok.val;
import no.faggruppe.java.PokemonAPI.controller.TrainerController;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Trainer.CreateTrainerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateTrainerComponentTest extends ComponentTestBase {
    @Autowired
    private TrainerController trainerController;

    @Test
    @DisplayName("Verifies creating trainer")
    void createsTrainer() {
        val trainerRequest = CreateTrainerRequest.builder()
                .trainerName("Even")
                .pokemonParty(new String[]{"Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck"})
                .pokemonStorage(new String[]{"Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck", "Psyduck"})
                .build();

        val result = trainerController.createTrainer(trainerRequest);

        assertThat(result.trainerName()).isEqualTo("Even");
        assertThat(result.partyPokemon()).hasSize(6);
        val distinctPartyPokemon = Arrays.stream(result.partyPokemon()).map(Pokemon::name).distinct().collect(toList());
        assertThat(distinctPartyPokemon).hasSize(1);
        assertThat(distinctPartyPokemon.get(0)).isEqualTo("Psyduck");
        assertThat(result.storagePokemon()).hasSize(13);
        val distinctStoredPokemon = Arrays.stream(result.storagePokemon()).map(Pokemon::name).distinct().collect(toList());
        assertThat(distinctStoredPokemon).hasSize(1);
        assertThat(distinctStoredPokemon.get(0)).isEqualTo("Psyduck");
    }
}
