package no.faggruppe.java.PokemonAPI.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "pokemonStorage")
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {
    @Id
    @Column(name = "ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "POKEDEX_ID")
    private int pokedexId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE_1")
    private String type1;

    @Column(name = "TYPE_2")
    private String type2;

    @Column(name = "TRAINER_ID")
    private String trainerID;

    @Column(name = "IN_PARTY")
    private boolean inParty;
}
