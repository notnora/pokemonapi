package no.faggruppe.java.PokemonAPI.component;

import no.faggruppe.java.PokemonAPI.PokemonApiApplicationTests;
import no.faggruppe.java.PokemonAPI.controller.PokemonController;
import no.faggruppe.java.PokemonAPI.repository.PokemonStorageRepository;
import no.faggruppe.java.PokemonAPI.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TrainerPokemonComponentTest extends PokemonApiApplicationTests {
    @Autowired
    private PokemonController pokemonController;

    @Autowired
    private PokemonStorageRepository pokemonStorageRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @BeforeEach
    public void setUp() {
        pokemonStorageRepository.deleteAll();
        trainerRepository.deleteAll();

    }

    @Test
    @DisplayName("Create a trainer with no pokemon")
    void CreateTrainerWithNoPokemon() {

    }

    @Test
    @DisplayName("Create trainer with one party pokemon and one storage pokemon")
    void CreateTrainerOnePartyPokemonOneStoragePokemon() {

    }


}
