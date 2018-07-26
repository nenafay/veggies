package org.wecancodeit.veggies;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(VegReviewController.class)
public class VegReviewControllerMockMVCTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private VegetableRepository veggieRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@MockBean
	private RecipeRepository recipeRepo;
	
	@Mock
	private Vegetable veggie;
	
	@Mock
	private Vegetable anotherVeggie;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag anotherTag;
	
	@Test
	public void shouldRouteToSingleVegView() throws Exception {
		long arbitraryVegReviewId = 1;
		when(veggieRepo.findById(arbitraryVegReviewId)).thenReturn(Optional.of(veggie));
		mvc.perform(get("/veggie?id=1")).andExpect(view().name(is("veggie")));
	}
	
	@Test
	public void shouldNotBeOkForSingleVegView() throws Exception {
		mvc.perform(get("/veggie?id=1")).andExpect(status().isNotFound());
	}
}