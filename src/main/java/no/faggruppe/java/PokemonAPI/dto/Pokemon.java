package no.faggruppe.java.PokemonAPI.dto;

import lombok.Builder;

@Builder
public record Pokemon(String name, String[] moves, String type) {
}
