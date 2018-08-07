package org.wecancodeit.veggies;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class VegReviewsJPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private VegetableRepository veggieRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private RecipeRepository recipeRepo;
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category", "imgUrl", "blurb"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category>result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getCategoryName(), is("category"));
		
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category", "imgUrl", "blurb"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadVeggie() {
		Category category = categoryRepo.save(new Category("category", "imgUrl", "blurb"));
		categoryRepo.save(category);
		
		Vegetable veggie = new Vegetable("veggie name", "imgUrl", "text", category);
		veggie = veggieRepo.save(veggie);
		long veggieId = veggie.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Vegetable> result = veggieRepo.findById(veggieId);
		veggie = result.get();
		assertThat(veggie.getVeggieName(), is("veggie name"));
	}
	
	@Test
	public void shouldEstablishVeggieToCategoryRelationship() {
		Category category = categoryRepo.save(new Category("category", "imgUrl", "blurb"));
		categoryRepo.save(category);
		long categoryId = category.getId();
		
		Vegetable carrots = veggieRepo.save(new Vegetable("carrots", "imgUrl", "text", category));
		 veggieRepo.save(carrots);
		 
		Vegetable peas = veggieRepo.save(new Vegetable("peas", "imgUrl", "text", category));
		veggieRepo.save(peas);
		
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getVeggies(),containsInAnyOrder(carrots, peas));
	}
	
	@Test
	public void shouldFindVeggiesForcategory() {
		Category roots = categoryRepo.save(new Category("Root Vegetables", "imgUrl", "blurb"));
		Category cruciferous = categoryRepo.save(new Category ("Cruciferous Vegetables", "imgUrl", "blurb"));
		
		Vegetable carrots = veggieRepo.save(new Vegetable("carrots", "imgUrl", "text", roots));
		Vegetable beets = veggieRepo.save(new Vegetable("beets", "imgUrl", "text", roots));
		Vegetable broccoli = veggieRepo.save(new Vegetable("broccoli", "imgUrl", "text", cruciferous));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Vegetable> veggiesForCategory = veggieRepo.findByCategory(roots);
		
		assertThat(veggiesForCategory, containsInAnyOrder(carrots, beets));
		
	}
	
	@Test
	public void shouldFindVeggiesForCategoryId() {
		Category roots = categoryRepo.save(new Category
				("Root Veggies", "imgUrl", "blurb"));
		long categoryId = roots.getId();
		Category cruciferous = categoryRepo.save(new Category 
				("Cruciferous Vegetables", "imgUrl", "blurb"));
		
		Vegetable carrots = veggieRepo.save(new Vegetable
				("carrots", "imgUrl", "text", roots));
		Vegetable beets = veggieRepo.save(new Vegetable
				("beets", "imgUrl", "text", roots));
		Vegetable broccoli = veggieRepo.save(new Vegetable
				("broccoli", "imgUrl", "text", cruciferous));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Vegetable>veggiesForCategory = veggieRepo.findByCategoryId(categoryId);
		
		assertThat(veggiesForCategory, containsInAnyOrder(carrots, beets));
	}
	
	@Test
	public void shouldSaveAndLoadTags() {
		Tag tag = tagRepo.save(new Tag("tag name"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getTagName(), is("tag name"));
	}
	
	@Test
	public void shouldGenerateTagId() {
		Tag tag = tagRepo.save(new Tag("tag name"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(tagId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishReviewToTagRelationship() {
		Tag raw = tagRepo.save(new Tag("good eaten raw"));
		Tag saucy = tagRepo.save(new Tag("great in sauces"));
		
		Category techFruit = categoryRepo.save(new Category
				("technically fruit", "imgUrl", "blurb"));
		
		Vegetable tomato = veggieRepo.save(new Vegetable
				("tomato", "imgUrl", "text", techFruit, raw, saucy));
		long veggieId = tomato.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Vegetable> result = veggieRepo.findById(veggieId);
		tomato = result.get();
		assertThat(tomato.getTags(), containsInAnyOrder(raw, saucy));
	}
	
	@Test
	public void shouldFindVeggiesForTagId() {
		Tag raw = tagRepo.save(new Tag("good eaten raw"));
		long tagId = raw.getId();
		
		Category techFruit = categoryRepo.save(new Category
				("technically fruit", "imgUrl", "blurb"));
		
		Vegetable tomato = veggieRepo.save(new Vegetable
				("tomato", "imgUrl", "text", techFruit, raw));
		Vegetable bellPepper = veggieRepo.save(new Vegetable
				("bellPepper", "imgUrl", "text", techFruit, raw));
		
		entityManager.flush();
		entityManager.clear();
		
		Tag savedTag = tagRepo.findById(tagId).get();
		Collection<Vegetable> veggiesForTag = veggieRepo.findByTagsContains(savedTag);
		
		assertThat(veggiesForTag, containsInAnyOrder(tomato, bellPepper));
	}
	
	@Test
	public void shouldSaveAndLoadRecipes() {
		Category roots = categoryRepo.save(new Category
				("root vegetables", "imgUrl", "blurg"));
		
		Vegetable carrot = veggieRepo.save(new Vegetable
				("Carrot", "imgUrl", "text", roots));
		
		Recipe recipe = recipeRepo.save(new Recipe("user name", "recipe title", "recipe", carrot));
		long recipeId = recipe.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Recipe> result = recipeRepo.findById(recipeId);
		recipe = result.get();
		assertThat(recipe.getRecipeTitle(), is("recipe title"));
		
		
	}
	
	@Test
	public void shouldGenerateCommentId() {
		Category roots = categoryRepo.save(new Category
				("root vegetables", "imgUrl", "blurg"));
		
		Vegetable carrot = veggieRepo.save(new Vegetable
				("Carrot", "imgUrl", "text", roots));
		
		Recipe recipe = recipeRepo.save(new Recipe("user name", "recipe title", "recipe", carrot));
		long recipeId = recipe.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(recipeId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishRecipesToVegetablesRelationship() {
		Category roots = categoryRepo.save(new Category
				("root vegetables", "imgUrl", "blurg"));
		Category techFruit = categoryRepo.save(new Category
				("technically fruit", "imgUrl", "blurb"));
		Category cruciferous = categoryRepo.save(new Category 
				("Cruciferous Vegetables", "imgUrl", "blurb"));
		
		Vegetable tomato = veggieRepo.save(new Vegetable
				("tomato", "imgUrl", "text", techFruit));
		Vegetable bellPepper = veggieRepo.save(new Vegetable
				("bellPepper", "imgUrl", "text", techFruit));
		Vegetable carrot = veggieRepo.save(new Vegetable
				("Carrot", "imgUrl", "text", roots));
		Vegetable kale = veggieRepo.save(new Vegetable
				("kale", "imgUrl", "text", cruciferous));
		
		Recipe minestrone = recipeRepo.save(new Recipe("user name", "Minestrone Soup", "recipe", carrot, kale, tomato, bellPepper));
		Recipe stirFry = recipeRepo.save(new Recipe("user name", "Stir Fry", "recipe", carrot, bellPepper, kale));
		
		long veggieId = carrot.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Vegetable>result = veggieRepo.findById(veggieId);
		carrot = result.get();
		assertThat(carrot.getRecipes(), containsInAnyOrder(minestrone, stirFry));
	}
}
