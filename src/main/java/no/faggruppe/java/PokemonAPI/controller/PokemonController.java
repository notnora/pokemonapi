package no.faggruppe.java.PokemonAPI.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.dto.Pokemon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/pokemon")
public class PokemonController {
    @GetMapping("/")
    public Pokemon[] getAllPokemons() {
        log.info("All pokemons");
        val allPokemons = new Pokemon[]{
                Pokemon.builder()
                        .name("bulbasaur")
                        .build(),
                Pokemon.builder()
                        .name("squirtle")
                        .build(),
                Pokemon.builder()
                        .name("charmander")
                        .build()};
        return allPokemons;
    }
}
