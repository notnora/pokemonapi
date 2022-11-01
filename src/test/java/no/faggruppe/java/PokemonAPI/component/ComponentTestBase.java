package no.faggruppe.java.PokemonAPI.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.faggruppe.java.PokemonAPI.PokemonApiApplicationTests;
import no.faggruppe.java.PokemonAPI.controller.PokemonController;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ComponentTestBase extends PokemonApiApplicationTests {
    @Autowired
    protected PokemonController pokemonController;

    @Autowired
    protected ObjectMapper objectMapper;
}
