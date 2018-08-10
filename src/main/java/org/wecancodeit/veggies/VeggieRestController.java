package org.wecancodeit.veggies;

import java.util.Optional;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VeggieRestController {
	
	@Resource
	private VeggieRepository veggieRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private RecipeRepository recipeRepo;

	@GetMapping("/tags")
	public Iterable<Tag> tags(){
		return tagRepo.findAll();
	}
	
	//get veggies by tag 
	@GetMapping("/veggies/{tagName}")
	public Collection<Veggie>findAllVeggiesByTag(
			@PathVariable(value="tagName")String tagName) throws TagNotFoundException{
		Optional<Tag> tagOptional = tagRepo.findByTagName(tagName);
			if(!tagOptional.isPresent()) {
				throw new TagNotFoundException();
			}
		Tag tag = tagOptional.get();	
		Collection<Veggie>veggies = veggieRepo.findByTagsContains(tag);
		return veggies;
	}
	
	@PostMapping("/newTag/{tagName}/{veggieName}")
	public Veggie addNewTagToVeggie(
			@PathVariable(value="tagName") String tagName,
			@PathVariable(value="veggieName")String veggieName
			) throws VeggieNotFoundException{
		//get vegetable
		Optional<Veggie> veggieOptional = veggieRepo.findByVeggieName(veggieName);
		if(!veggieOptional.isPresent()) {
			throw new VeggieNotFoundException();
		}
		Veggie veggie = veggieOptional.get();
		
		//see if tag exists
		Tag tag;
		Optional<Tag>tagOptional = tagRepo.findByTagName(tagName);
		//create tag if necessary
		if(tagOptional.isPresent()) {
			tag = tagOptional.get();
		}
		else {
		tag = tagRepo.save(new Tag(tagName));
		}
		veggie.getTags().add(tag);
		veggieRepo.save(veggie);
	
		
		return veggie;
	}
	//add veggies to tag
	@PostMapping("/tag/{tagId}/{veggieName}")
	public Tag addVeggiesToTag(
			@PathVariable(value="tagId") long tagId,
			@PathVariable(value="veggieName")String veggieName
			) {
		//get tag
		Tag tag = tagRepo.findByTagId(tagId);
		//get vegetables
		Veggie veggies = veggieRepo.findByVeggieName(veggieName).get();
		//add vegetables to tag
		tag.getVeggies().add(veggies);
		tagRepo.save(tag);
		
		return tag;
	}
	
	@GetMapping("/recipes/{veggieName}")
	public Collection<Recipe>findAllRecipesByVeggie(
			@PathVariable(value="veggieName")String veggieName){
		Veggie veggie = veggieRepo.findByVeggieName(veggieName).get();
		Collection<Recipe>recipes = recipeRepo.findByVeggie(veggie);
		return recipes;
	}
	
	//add new Recipe to Veggies//
	@PostMapping("/newRecipe/{recipeName}/{veggieName}")
	public Veggie addNewRecipeToVeggie(
			@PathVariable(value="recipeName") String recipeName,
			@PathVariable(value="veggieName")String veggieName
				){
			//get vegetable
			Veggie veggie = veggieRepo.findByVeggieName(veggieName).get();
			Recipe recipe = recipeRepo.save(new Recipe(recipeName));
			veggie.getRecipes().add(recipe);
			veggieRepo.save(veggie);
		
			
			return veggie;
	}
	
}



