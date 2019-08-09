package hu.hwsw.airportapp.web;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.hwsw.airportapp.Airport2Application;
import hu.hwsw.airportapp.web.dto.airport.AirportDTO;
import hu.hwsw.airportapp.web.dto.airport.NewAirportDTO;

@SpringBootTest(classes = Airport2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase
class AirportControllerIT {

	@LocalServerPort
	private int serverPort;

	private WebTestClient client;

	@BeforeEach
	void setup() {
		client = WebTestClient
				.bindToServer().baseUrl(String.format("http://localhost:%d/api/v1", serverPort))
				.responseTimeout(Duration.ofMinutes(1)).build();
	}

	@Test
	void createValidAirport() {
		NewAirportDTO newAirport = new NewAirportDTO("Budapest Airport", "BUD");

		AirportDTO result = 
				client.post()
					.uri("/airports")
					.syncBody(newAirport)
					.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody(AirportDTO.class).returnResult().getResponseBody();

		assertNotNull(result.getId());
		assertEquals(newAirport.getName(), result.getName());
		assertEquals(newAirport.getIata(), result.getIata());
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getModifiedAt());
	}

	@ParameterizedTest
	@ValueSource(strings = { "", "BU", "BUDA" })
	public void createAnInvalidAirports(String iata) {
		NewAirportDTO newAirport = new NewAirportDTO("Budapest", iata);

		client.post()
			.uri("/airports")
			.syncBody(newAirport).accept(MediaType.APPLICATION_JSON_UTF8).exchange()
				.expectStatus().isBadRequest();
	}

}