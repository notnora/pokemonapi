package no.faggruppe.java.PokemonAPI.dto;

public record PokeApiPokemonResponse (int count, String next, String previous, PokemonResult[] results){
}
