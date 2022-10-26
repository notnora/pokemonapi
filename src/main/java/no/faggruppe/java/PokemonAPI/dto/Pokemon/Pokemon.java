package no.faggruppe.java.PokemonAPI.dto.Pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record Pokemon(
        int id,
        String name,
        PokemonMove[] moves,
        PokemonType[] types,
        String trainerID) {
}
