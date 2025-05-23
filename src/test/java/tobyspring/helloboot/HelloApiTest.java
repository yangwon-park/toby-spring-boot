package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
	@Test
	void hello() {
		// http localhost:8080/hello?name=Spring
		TestRestTemplate rest = new TestRestTemplate();

		ResponseEntity<String> res =
				rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

		// 검증
		// status 200
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

		// header(content-type) text/plain
		assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
		// body Hello Spring
		assertThat(res.getBody()).isEqualTo("*Hello Spring*");
	}

	@Test
	void failsHello() {
		// http localhost:8080/hello?name=Spring
		TestRestTemplate rest = new TestRestTemplate();

		ResponseEntity<String> res =
				rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "");

		// 검증
		// status 200
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}