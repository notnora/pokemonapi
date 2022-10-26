package no.faggruppe.java.PokemonAPI.db;

import no.faggruppe.java.PokemonAPI.repository.PokemonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PokemonStorageTestRepository extends CrudRepository<PokemonEntity, Long> {
}
