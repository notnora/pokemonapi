package no.faggruppe.java.PokemonAPI.dto.Pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonMove (Move move){ }
