package org.wecancodeit.veggies;

import java.util.Optional;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/tags")
	public Iterable<Tag> findAllTags(){
		return tagRepo.findAll();
	
	}

	@PutMapping("/addTag/{id}")
	public Iterable<Tag> addVeggieTag(@RequestBody TagUpdateRequest addTag){
		Optional<Vegetable>vegOptional = veggieRepo.findById(addTag.veggieId);
		
		if(!vegOptional.isPresent()) {
			Optional<Tag>tagOptional = tagRepo.findFirstByName(addTag.tagName);
			Tag newTag;
			
			if(!tagOptional.isPresent()) {
				newTag = tagRepo.save(new Tag(addTag.tagName));
			} else {
				newTag = tagOptional.get();
			}
			
			Vegetable veggie = vegOptional.get();
			Collection<Tag> veggieTags = veggie.getTags();
			
			if(!veggieTags.contains(newTag)) {
				veggieTags.add(newTag);
				veggieRepo.save(veggie);
			}
		}
		return tagRepo.findByVeggiesId(vegOptional);
	}
	
	@DeleteMapping("/remove-tag")
	public Iterable<Tag> removeTag (@RequestBody TagUpdateRequest removeTag){
		Optional<Tag> tagOptional = tagRepo.findFirstByName(removeTag.tagName);
		Tag tag = tagOptional.get();
		
		Optional<Vegetable> vegOptional = veggieRepo.findById(removeTag.veggieId);
		Vegetable veggie = vegOptional.get();
		
		try {
			veggie.deleteTag(tag);
			veggieRepo.save(veggie);
		} catch (Exception e) {
		}
		return tagRepo.findByVeggiesId(vegOptional);
		}
	}



