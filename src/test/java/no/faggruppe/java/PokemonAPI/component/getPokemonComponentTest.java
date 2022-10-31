package no.faggruppe.java.PokemonAPI.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import lombok.val;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.PokemonMove;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.PokemonType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class getPokemonComponentTest extends ComponentTestBase {

    @Test
    void getPokemonByNameFromPokeAPIJSONStubOk() throws JsonProcessingException {
        /**
         * bulbasaur_pokeApie_ok.json vil treffe inn er og
         * stubbe kallet mot pokeApiet.
         * Man kan sette priority for å bestemme hvilken stub som har prioritet
         */
        pokemonController.getPokemonByName("bulbasaur");

        List<ServeEvent> allServeEvents = getAllServeEvents();
        /**
         * Vanlig assertThat vil stoppe kjøring
         * Softly lar den kjøre videre
         */
      /*
        assertThat(allServeEvents.size()).isEqualTo(1);
        assertThat(allServeEvents.get(0).getRequest().getUrl()).isEqualTo("/api/v2/pokemon/bulbasaur");
        assertThat(allServeEvents.get(0).getRequest().getUrl()).isEqualTo("/api/v2/pokemon/squirtle");
     */
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(allServeEvents.size()).isEqualTo(1);
            softly.assertThat(allServeEvents.get(0).getRequest().getUrl()).isEqualTo("/api/v2/pokemon/bulbasaur");
            // softly.assertThat(allServeEvents.get(0).getRequest().getUrl()).isEqualTo("/api/v2/pokemon/squirtle");
        });
    }

    @Test
    void getPokemonByNameFromPokeAPIJavaStubOk() throws JsonProcessingException {
        val squirtle = Pokemon.builder()
                .name("squirtle")
                .id(7)
                .types(new PokemonType[]{})
                .moves(new PokemonMove[]{})
                .build();
        stubFor(get(urlMatching("/api/v2/pokemon/squirtle"))
                .willReturn(aResponse()
                        .withBody(objectMapper.writeValueAsString(squirtle)))
        );

    }
}
