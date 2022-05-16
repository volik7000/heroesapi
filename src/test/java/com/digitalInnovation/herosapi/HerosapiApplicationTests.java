package com.digitalInnovation.herosapi;

import com.digitalInnovation.herosapi.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import static com.digitalInnovation.herosapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HerosapiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;


	@Test
	public void getOneHeroesById() {

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
				.exchange()
				.expectStatus().isOk()
				.expectBody();

	}

	@Test
	public void getOneHeroNotFound() {

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),1)
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void deleteHero() {

		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound();
	}
}
