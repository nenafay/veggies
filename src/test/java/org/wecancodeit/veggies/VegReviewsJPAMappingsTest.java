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
		
		Veggie veggie = new Veggie("veggie name", "imgUrl", "text", category);
		veggie = veggieRepo.save(veggie);
		long veggieId = veggie.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Veggie> result = veggieRepo.findById(veggieId);
		veggie = result.get();
		assertThat(veggie.getVeggieName(), is("veggie name"));
	}
	
	@Test
	public void shouldEstablishVeggieToCategoryRelationship() {
		Category category = categoryRepo.save(new Category("category", "imgUrl", "blurb"));
		categoryRepo.save(category);
		long categoryId = category.getId();
		
		Veggie carrots = veggieRepo.save(new Veggie("carrots", "imgUrl", "text", category));
		 veggieRepo.save(carrots);
		 
		Veggie peas = veggieRepo.save(new Veggie("peas", "imgUrl", "text", category));
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
		
		Veggie carrots = veggieRepo.save(new Veggie("carrots", "imgUrl", "text", roots));
		Veggie beets = veggieRepo.save(new Veggie("beets", "imgUrl", "text", roots));
		Veggie broccoli = veggieRepo.save(new Veggie("broccoli", "imgUrl", "text", cruciferous));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Veggie> veggiesForCategory = veggieRepo.findByCategory(roots);
		
		assertThat(veggiesForCategory, containsInAnyOrder(carrots, beets));
		
	}
	
}
