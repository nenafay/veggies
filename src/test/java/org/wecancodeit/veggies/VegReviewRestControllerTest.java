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
	public void shouldBeOkForAllVeggiesByTag() {
		ResponseEntity<String> response = restTemplate.getForEntity("/veggies/Try me raw.", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status,is(HttpStatus.OK));
	}
	
	@Test
	public void ShouldBeOkForNewVeggieTag() throws URISyntaxException {
		ResponseEntity<String> response = restTemplate.postForEntity("/newTag/raw/Tomato", null, String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status,is(HttpStatus.OK));
		
	}
	
	@Test
	public void ShouldBeOkForRecipesByVeggie() {
		ResponseEntity<String> response = restTemplate.getForEntity("/recipes/Tomato", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status,is(HttpStatus.OK));
	}
	@Test
	public void ShouldBeOkForNewRecipe() {
		ResponseEntity<String> response = restTemplate.postForEntity("/newRecipe/recipeName/Tomato", null, String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status,is(HttpStatus.OK));
	}
	
	
}
