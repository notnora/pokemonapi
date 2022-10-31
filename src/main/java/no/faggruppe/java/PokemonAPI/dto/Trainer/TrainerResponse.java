package no.faggruppe.java.PokemonAPI.dto.Trainer;

import lombok.Builder;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;

@Builder
public record TrainerResponse(String trainerName, Pokemon[] partyPokemon, Pokemon[] storagePokemon, String message){}
