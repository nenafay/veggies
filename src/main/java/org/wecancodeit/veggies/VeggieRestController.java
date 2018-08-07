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
@RequestMapping("/veggie")
public class VeggieRestController {
	
	@Resource
	private VegetableRepository veggieRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private RecipeRepository recipeRepo;
	
	@GetMapping("/allTags")
	public Iterable<Tag> allTags(){
		return tagRepo.findAll();
	}
	
	//get veggies by tag 
	@RequestMapping("/veggies/{tagName}")
	public Collection<Vegetable>findAllVeggiesByTag(@PathVariable(value="tagName")String tagName) throws TagNotFoundException{
		Optional<Tag> tagOptional = tagRepo.findByTagName(tagName);
			if(!tagOptional.isPresent()) {
				throw new TagNotFoundException();
			}
		Tag tag = tagOptional.get();	
		Collection<Vegetable>veggies = veggieRepo.findByTagsContains(tag);
		return veggies;
	}
	
	@PostMapping("/newTag/{tagName}/{veggieName}")
	public Vegetable addNewTagToVeggies(
			@PathVariable(value="tagName") String tagName,
			@PathVariable(value="veggieName")String veggieName
			){
		//get vegetable
		Vegetable veggie = veggieRepo.findByVeggieName(veggieName);
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
}



