package no.faggruppe.java.PokemonAPI.repository;

import org.springframework.data.repository.CrudRepository;

public interface TrainerRepository extends CrudRepository<TrainerEntity, Long> {
    TrainerEntity findTrainerEntityById(String id);
}
