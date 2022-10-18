package no.faggruppe.java.PokemonAPI.dto.Pokemon;

public record PokeApiPokemonResponse (int count, String next, String previous, PokemonResult[] results){
}
