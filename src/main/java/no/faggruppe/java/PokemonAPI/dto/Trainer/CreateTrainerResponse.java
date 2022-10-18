package no.faggruppe.java.PokemonAPI.dto.Trainer;

import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;

public record CreateTrainerResponse (String trainerName, Pokemon[] pokemonActive, Pokemon[] pokemonStorage, String message){}
