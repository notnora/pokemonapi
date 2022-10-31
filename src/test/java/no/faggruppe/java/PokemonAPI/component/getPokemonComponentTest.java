package no.faggruppe.java.PokemonAPI.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;


public class getPokemonComponentTest extends ComponentTestBase {

    @Test
    void getPokemonByNameFromPokeAPIJSONFileOk() throws JsonProcessingException {
        /**
         * bulbasaur_pokeApie_ok.json vil treffe inn er og
         * stubbe kallet mot pokeApiet.
         */
        pokemonController.getPokemonByName("bulbasaur");

        SoftAssertions.assertSoftly(softly -> {
            System.out.println(softly);
        });
    }
}
