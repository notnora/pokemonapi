package no.faggruppe.java.PokemonAPI.config.props.app;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Builder
public class AppProps {
    @NestedConfigurationProperty
    private Endpoints endpoints;
}
