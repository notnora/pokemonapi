package no.faggruppe.java.PokemonAPI.dto.Pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record Pokemon(
        String name,
        PokemonMove[] moves,
        PokemonType[] types) {
}
