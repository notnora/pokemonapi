package no.faggruppe.java.PokemonAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.PokemonResult;
import no.faggruppe.java.PokemonAPI.repository.PokemonEntity;
import no.faggruppe.java.PokemonAPI.service.PokemonRepositoryService;
import no.faggruppe.java.PokemonAPI.service.PokemonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;
    private final PokemonRepositoryService pokemonRepositoryService;

    @GetMapping("/")
    public PokemonResult[] getAllPokemons() {
        val pokemons = pokemonService.getAllPokemons();
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
        return pokemons;
    }

    @GetMapping("/{pokemonName}")
    public Pokemon getPokemonByName(@PathVariable("pokemonName") String pokemonName) throws JsonProcessingException {
        val pokemon = pokemonService.getPokemon(pokemonName);
        log.info("Looking up pokemon: {}", pokemon);
        return pokemon;
    }

    @GetMapping("/db/all")
    public List<Pokemon> getAllStoredPokemon() {
        return pokemonRepositoryService.getAllPokemonFromDb();
    }
}
