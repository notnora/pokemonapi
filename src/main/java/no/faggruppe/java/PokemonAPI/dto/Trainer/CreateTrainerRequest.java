package no.faggruppe.java.PokemonAPI.dto.Trainer;

public record CreateTrainerRequest (String trainerName, String[] pokemonActive, String[] pokemonStorage){
}
