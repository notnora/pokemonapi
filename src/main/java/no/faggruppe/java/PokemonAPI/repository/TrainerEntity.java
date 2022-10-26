package no.faggruppe.java.PokemonAPI.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "trainer")
@AllArgsConstructor
@NoArgsConstructor
public class TrainerEntity {
    @Id
    @Column(name = "ID")
    private String id;
}
