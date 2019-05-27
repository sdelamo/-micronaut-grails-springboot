package example.springboot

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertFalse

@RunWith(SpringRunner)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTests {

	@Autowired
	private WebTestClient webClient

	@Test
	void validateControllerCanUseASharedService() {
		String uri = generateUri('es','B99286353')
		this.webClient.get().uri(uri).exchange().expectStatus().isOk().expectBody(Boolean)
				.consumeWith({response -> assertTrue(response.getResponseBody())})

		uri = generateUri('es','B19280031')
		this.webClient.get().uri(uri).exchange().expectStatus().isOk().expectBody(Boolean)
				.consumeWith({response -> assertTrue(response.getResponseBody())})

		uri = generateUri('es','XXXXXXXXX')
		this.webClient.get().uri(uri).exchange().expectStatus().isOk().expectBody(Boolean)
				.consumeWith({response -> assertFalse(response.getResponseBody())})
	}

    String generateUri(String memberState, String vatNumber ) {
		UriComponentsBuilder.fromUriString('/vies/valid')
				.queryParam('memberStateCode', memberState)
				.queryParam('vatNumberCode', vatNumber)
				.toUriString()
	}
}