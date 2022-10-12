package no.faggruppe.java.PokemonAPI.dto;

import lombok.Builder;

@Builder
public record Trainer(String name, Pokemon[] activePokemons, Pokemon[] storagePokemon) {
}
