package no.faggruppe.java.PokemonAPI.repository;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface PokemonRepository extends Repository<PokemonEntity, Long> {
    List<PokemonEntity> findAll();
}
