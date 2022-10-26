package no.faggruppe.java.PokemonAPI.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PokemonStorageRepository extends CrudRepository<PokemonEntity, Long> {
    List<PokemonEntity> findAllByTrainerID(String trainerID);
    List<PokemonEntity> findAllByTrainerIDAndInParty(String trainerID, boolean inParty);
}
