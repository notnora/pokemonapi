package no.faggruppe.java.PokemonAPI.service;

import lombok.RequiredArgsConstructor;
import no.faggruppe.java.PokemonAPI.repository.TrainerEntity;
import no.faggruppe.java.PokemonAPI.repository.TrainerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainerRepositoryService {
    private final TrainerRepository trainerRepository;

    public void createTrainer(String trainerName) {
        trainerRepository.save(TrainerEntity.builder()
                .id(trainerName)
                .build());
    }
}
