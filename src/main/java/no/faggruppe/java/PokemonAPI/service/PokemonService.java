package no.faggruppe.java.PokemonAPI.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.consumer.PokeAPIConsumer;
import no.faggruppe.java.PokemonAPI.dto.Pokemon;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonService {
    private final PokeAPIConsumer pokeAPIConsumer;

    public Pokemon[] getAllPokemons() {
        val allPokemonsResponse = pokeAPIConsumer.getAllPokemons();
        log.info(String.valueOf(allPokemonsResponse));
        return new Pokemon[0];
    }
}
