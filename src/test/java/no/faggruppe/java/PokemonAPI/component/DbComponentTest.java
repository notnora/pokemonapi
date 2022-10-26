package no.faggruppe.java.PokemonAPI.component;

import lombok.val;
import no.faggruppe.java.PokemonAPI.PokemonApiApplicationTests;
import no.faggruppe.java.PokemonAPI.controller.PokemonController;
import no.faggruppe.java.PokemonAPI.db.PokemonStorageTestRepository;
import no.faggruppe.java.PokemonAPI.repository.PokemonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class DbComponentTest extends PokemonApiApplicationTests {
    @Autowired
    private PokemonController pokemonController;

    @Autowired
    private PokemonStorageTestRepository pokemonTestRepository;

    @BeforeEach
    public void setUp() {
        pokemonTestRepository.deleteAll();
    }

    @Test
    @DisplayName("Verifies fetching stored pokemon")
    void fetchesStoredPokemon() {
        pokemonTestRepository.save(PokemonEntity.builder()
                .id(UUID.randomUUID())
                .pokedexId(1)
                .name("Bulbasaur")
                .type1("Grass")
                .type2("Poison")
                .build());

        val result = pokemonController.getAllStoredPokemon();

        assertThat(result).hasSize(1);
        val pokemon = result.get(0);
        assertThat(pokemon.name()).isEqualTo("Bulbasaur");
        val collect = Arrays.stream(pokemon.types())
                .map(t -> t.type().name())
                .collect(toList());
        assertThat(collect).containsExactlyInAnyOrder("Grass", "Poison");
    }
}
