package no.faggruppe.java.PokemonAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.consumer.PokeAPIConsumer;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.PokemonResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonService {
    private final PokeAPIConsumer pokeAPIConsumer;
    private final PokemonStorageRepositoryService pokemonStorageRepositoryService;

    public PokemonResult[] getAllPokemons() {
        val allPokemonsResponse = pokeAPIConsumer.getAllPokemons();
        return allPokemonsResponse.results();
    }
    public Pokemon getPokemon (String name) throws JsonProcessingException {
        val pokemon = pokeAPIConsumer.getPokemonFromName(name);
        return pokemon;
    }

    public List<Pokemon> getAllStoredPokemon() {
        return pokemonStorageRepositoryService.getAllPokemonFromDb();
    }
}
