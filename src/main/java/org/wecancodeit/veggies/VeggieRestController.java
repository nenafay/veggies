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
	
	@PostMapping("/newTag/{name}")
	public Iterable<Tag> addNewTagToVeggies(
			@PathVariable("name") String veggieName){
		Optional<Vegetable> veggieOptional = veggieRepo.findBy
	}
}



