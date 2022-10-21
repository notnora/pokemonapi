package no.faggruppe.java.PokemonAPI.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.PokemonType;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Type;
import no.faggruppe.java.PokemonAPI.repository.PokemonEntity;
import no.faggruppe.java.PokemonAPI.repository.PokemonRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PokemonRepositoryService {
    private final PokemonRepository pokemonRepository;

    public List<Pokemon> getAllPokemonFromDb() {
        val pokemonEntities = pokemonRepository.findAll();
        return mapToDto(pokemonEntities);
    }

    private List<Pokemon> mapToDto(List<PokemonEntity> pokemonEntities) {
        return pokemonEntities.stream()
                .map(this::toPokemon)
                .collect(Collectors.toList());
    }

    private Pokemon toPokemon(PokemonEntity entity) {
        return Pokemon.builder()
                .name(entity.getName())
                .types(mapTypes(entity))
                .build();
    }

    private PokemonType[] mapTypes(PokemonEntity entity) {
        return Stream.of(entity.getType1(), entity.getType2())
                .filter(Objects::nonNull)
                .map(Type::new)
                .map(type -> new PokemonType("slot?", type))
                .toArray(PokemonType[]::new);
    }
}
