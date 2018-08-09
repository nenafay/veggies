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
	private VeggieRepository veggieRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@MockBean
	private RecipeRepository recipeRepo;
	
	@Mock
	private Veggie veggie;
	
	@Mock
	private Veggie anotherVeggie;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag anotherTag;
	
	@Test
	public void shouldRouteToSingleVeggieView() throws Exception {
		long arbitraryVeggieId = 19;
		when(veggieRepo.findById(arbitraryVeggieId)).thenReturn(Optional.of(veggie));
		mvc.perform(get("/veggie?id=19")).andExpect(view().name(is("veggie")));
	}
	
	@Test
	public void shouldNotBeOkForSingleVeggieView() throws Exception {
		mvc.perform(get("/veggie?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}
	
	@Test
	public void shouldRouteToSingleTagView() throws Exception {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}
}
