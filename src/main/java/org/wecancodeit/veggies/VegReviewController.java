package org.wecancodeit.veggies;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VegReviewController {
	
	@Resource
	VegetableRepository veggieRepo;
	
	@Resource
	CategoryRepository categoryRepo;
	
	@Resource
	TagRepository tagRepo;
	
	@Resource
	RecipeRepository recipeRepo;
	
	@RequestMapping("/veggie")
	public String findOneVeggie (@RequestParam (value = "id") 
			long id, Model model)throws VeggieNotFoundException {
		Optional<Vegetable>veggie = veggieRepo.findById(id);
		
		if(veggie.isPresent()) {
			model.addAttribute("veggies", veggie.get());
			return("veggie");
		}
		throw new VeggieNotFoundException();
	}
	
	@RequestMapping("/show-veggies")
	public String findAllVeggies(Model model) {
		model.addAttribute("veggies", veggieRepo.findAll());
		return("veggies");
	}
	
	@RequestMapping("/category")
	public String findOneCategory (@RequestParam (value = "id")
			long id, Model model)throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(id);	
		
		if(category.isPresent()) {
			model.addAttribute("categories", category.get());
			return("category");
		}
		throw new CategoryNotFoundException();
	}
	
	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return("categories");
	}
	
	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value = "id")
			long id, Model model)throws TagNotFoundException {
		Optional<Tag>tag = tagRepo.findById(id);
		
		if(tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			return("tag");
		}
		throw new TagNotFoundException();
	}
	
	@RequestMapping("/")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return("tags");
	}
}
