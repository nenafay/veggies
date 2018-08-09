package org.wecancodeit.veggies;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VegReviewRestControllerTest {
	@Resource
	private TestRestTemplate restTemplate;
	
	@Test
	public void shouldBeOkForAllTags() {
		ResponseEntity<String> response = restTemplate.getForEntity("/tags", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status,is(HttpStatus.OK));
	}
	
	@Test
	public void shouldBeOkForVeggieTags() {
		ResponseEntity<String> response = restTemplate.getForEntity("/tags/9", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status,is(HttpStatus.OK));
	}
	
	@Test
	public void ShouldBeOkForNewTag() throws URISyntaxException {
		URI putUri = new URI("/veggie/add-tag");
		
		String json = "{\"id\":\"9\", \"tagName\":\"raw\"}";
		
		RequestEntity<String> request = RequestEntity.put(putUri)
				.header("Content-Type", "application/json")
				.accept(MediaType.APPLICATION_JSON).body(json);
		
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
		
	}
	
	@Test
	public void ShouldBeOkForAllRecipes() {
		ResponseEntity<String> response = restTemplate.getForEntity("/recipes", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status,is(HttpStatus.OK));
	}
	
	
}
