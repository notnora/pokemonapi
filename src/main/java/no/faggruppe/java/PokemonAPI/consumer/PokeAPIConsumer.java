package no.faggruppe.java.PokemonAPI.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import no.faggruppe.java.PokemonAPI.config.props.app.AppProps;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.PokeApiPokemonResponse;
import no.faggruppe.java.PokemonAPI.dto.Pokemon.Pokemon;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
@RequiredArgsConstructor
public class PokeAPIConsumer {

    private final WebClient webClient;
    private final AppProps appProps;

    public PokeApiPokemonResponse getAllPokemons() {
        val pokemons = webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(appProps.getEndpoints().getPokeApi() + "/pokemon")
                        .build().toUri())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokeApiPokemonResponse.class)
                .block();
        log.info("jkljkl");
        log.info("pokemons: {}", pokemons);
        return pokemons;
    }
    public Pokemon getPokemonFromName(String name) {
        log.info("Pokemon: {}", name);
        val pokemon = webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(appProps.getEndpoints().getPokeApi()+"pokemon/" + name)
                        .build().toUri())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Pokemon.class)
                .block();
        return pokemon;
    }
}
