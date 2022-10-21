package no.faggruppe.java.PokemonAPI.repository;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "pokemon")
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE_1")
    private String type1;

    @Column(name = "TYPE_2")
    private String type2;
}
