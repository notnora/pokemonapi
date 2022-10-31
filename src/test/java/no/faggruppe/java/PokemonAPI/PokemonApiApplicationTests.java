package no.faggruppe.java.PokemonAPI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
public class PokemonApiApplicationTests {
	@Test
	void contextLoads() {
	}

}
