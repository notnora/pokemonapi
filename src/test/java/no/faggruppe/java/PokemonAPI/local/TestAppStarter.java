package no.faggruppe.java.PokemonAPI.local;

import lombok.val;
import no.faggruppe.java.PokemonAPI.PokemonApiApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = PokemonApiApplication.class)
public class TestAppStarter {
    public static void main(String[] args) {
        val springApplication = new SpringApplication(TestAppStarter.class);
        springApplication.setAdditionalProfiles("test", "local");
        springApplication.run(args);
    }
}
