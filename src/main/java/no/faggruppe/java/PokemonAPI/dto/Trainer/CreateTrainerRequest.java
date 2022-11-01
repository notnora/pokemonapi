package no.faggruppe.java.PokemonAPI.dto.Trainer;


import lombok.Builder;

@Builder
public record CreateTrainerRequest(String trainerName, String[] pokemonParty, String[] pokemonStorage) {
}
