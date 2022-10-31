package no.faggruppe.java.PokemonAPI.dto.Pokemon;

import lombok.Builder;

@Builder
public record PokemonType (String slot,Type type){}
