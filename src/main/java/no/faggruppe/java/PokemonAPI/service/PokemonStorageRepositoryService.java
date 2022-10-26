package no.faggruppe.java.PokemonAPI.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.PokemonType;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Type;
import no.faggruppe.java.PokemonAPI.repository.PokemonEntity;
import no.faggruppe.java.PokemonAPI.repository.PokemonStorageRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class PokemonStorageRepositoryService {
    private final PokemonStorageRepository pokemonRepository;

    public void saveStoragePokemon(Pokemon pokemon, String trainerName, boolean inParty) {
        pokemonRepository.save(mapToPokemonEntity(pokemon, inParty, trainerName));
    }

    public void saveAllStoragePokemon(List<Pokemon> pokemons, String trainerName, boolean inParty) {
        // Mapper om alle pokemonene til en PokemonEntity og deretter lagrer alt i db via repository
        List<PokemonEntity> pokemonEntityList = pokemons.stream()
                .map(pokemon -> mapToPokemonEntity(pokemon, inParty, trainerName))
                .collect(toList());
        pokemonRepository.saveAll(pokemonEntityList);
    }

    private PokemonEntity mapToPokemonEntity(Pokemon pokemon, boolean inParty, String trainerName) {
        return PokemonEntity.builder()
                .id(UUID.randomUUID())
                .pokedexId(pokemon.id())
                .inParty(inParty)
                .trainerID(trainerName)
                .type1(getType(0, pokemon))
                .type2(getType(1, pokemon))
                .name(pokemon.name())
                .build();
    }

    private String getType(int i, Pokemon pokemon) {
        return i <= pokemon.types().length - 1
                ? pokemon.types()[i].type().name()
                : null;
    }

    public List<Pokemon> getAllPokemonFromDb() {
        val pokemonEntities = pokemonRepository.findAll();
        return mapToDto((List<PokemonEntity>) pokemonEntities);
    }

    public List<Pokemon> getAllPokemonByTrainer(String trainerID, boolean inParty) {
        val pokemonEntities = pokemonRepository.findAllByTrainerIDAndInParty(trainerID, inParty);
        return mapToDto(pokemonEntities);
    }

    private List<Pokemon> mapToDto(List<PokemonEntity> pokemonEntities) {
        return pokemonEntities.stream()
                .map(this::toPokemon)
                .collect(toList());
    }

    private Pokemon toPokemon(PokemonEntity entity) {
        return Pokemon.builder()
                .name(entity.getName())
                .id(entity.getPokedexId())
                .types(mapTypes(entity))
                .trainerID(entity.getTrainerID())
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
