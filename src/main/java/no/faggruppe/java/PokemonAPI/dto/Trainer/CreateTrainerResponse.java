package no.faggruppe.java.PokemonAPI.dto.Trainer;

import lombok.Builder;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;

@Builder
public record CreateTrainerResponse (String trainerName, Pokemon[] pokemonActive, Pokemon[] pokemonStorage, String message){}