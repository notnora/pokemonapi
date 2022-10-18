package no.faggruppe.java.PokemonAPI.dto.Pokemon;

import lombok.Builder;

@Builder
public record Trainer(String name, Pokemon[] activePokemons, Pokemon[] storagePokemon) {
}
