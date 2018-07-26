package org.wecancodeit.veggies;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class VegReviewControllerTest {

	@InjectMocks
	private VegReviewController underTest;
	
	@Mock
	private Vegetable veggie;
	
	@Mock
	private Vegetable anotherVeggie;
	
	@Mock
	private VegetableRepository veggieRepo;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag anotherTag;
	
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private Recipe recipe;
	
	@Mock
	private Recipe anotherRecipe;
	
	@Mock
	private RecipeRepository recipeRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleVegReviewToModel() throws VeggieNotFoundException{
		long arbitraryVeggieId = 1L;
		when(veggieRepo.findById(arbitraryVeggieId)).thenReturn(Optional.of(veggie));
		
		underTest.findOneVeggie(arbitraryVeggieId, model);
		verify(model).addAttribute("veggies", veggie);
	
	}
	
	@Test
	public void shouldAddAllVegReviewsToModel() {
		Collection<Vegetable> allVeggies = Arrays.asList(veggie, anotherVeggie);
		when(veggieRepo.findAll()).thenReturn(allVeggies);
		
		underTest.findAllVeggies(model);
		verify(model).addAttribute("veggies", allVeggies);
	}
	
	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException{
		long arbitraryCategoryId = 5;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		
		underTest.findOneCategory(arbitraryCategoryId,model);
		verify(model).addAttribute("categories", category);
	}
	
	@Test
	public void shouldAddAllCategoriesToModel(){
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	@Test
	public void shouldAddSingleTagToModel() throws TagNotFoundException{
		long arbitraryTagId = 7;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		
		underTest.findOneTag(arbitraryTagId,model);
		verify(model).addAttribute("tags", tag);
	}
	
	@Test
	public void shouldAddAllTagsToModel() {
		Collection<Tag> allTags = Arrays.asList(tag, anotherTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		underTest.findAllTags(model);
		verify(model).addAttribute("tags", allTags);
	}
}
