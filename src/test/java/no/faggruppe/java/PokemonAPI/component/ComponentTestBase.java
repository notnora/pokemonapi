package no.faggruppe.java.PokemonAPI.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.faggruppe.java.PokemonAPI.PokemonApiApplicationTests;
import no.faggruppe.java.PokemonAPI.controller.PokemonController;
import org.springframework.beans.factory.annotation.Autowired;

public class ComponentTestBase extends PokemonApiApplicationTests {
    @Autowired
    PokemonController pokemonController;

    @Autowired
    ObjectMapper objectMapper;
}
