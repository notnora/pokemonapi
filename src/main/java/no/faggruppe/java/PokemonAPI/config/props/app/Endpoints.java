package no.faggruppe.java.PokemonAPI.config.props.app;

import lombok.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endpoints {
    private String pokeApi;
}
