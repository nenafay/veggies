package org.wecancodeit.veggies;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProducePopulator implements CommandLineRunner {

	@Resource
	private VegetableRepository veggieRepo;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Override
	public void run(String...args) throws Exception {
		Category techFruit = categoryRepo.save(new Category("Technically a Fruit", 
				"/images/r.jpg",
				"Not a leaf. Not a root. Not a stem. Not a seed. Technically, it's a fruit. Doesn't matter to me! "));
		
		Category roots = categoryRepo.save(new Category("Root Vegetables", 
				"/images/roots.jpg",
				"Versatile, earthy, and easy to store."));
		
		Category squashMelon = categoryRepo.save(new Category("Squashes & Melons", 
				"/images/squash.jpg",
				"Yes, they're related. Yes, they're also fruit, but that category was too huge!"));
		
		Category cruciferous = categoryRepo.save(new Category("Cruciferous Vegetables", 
				"/images/cruciferous.jpg",
				"Edible leaves, stems, and even flowers!"));
		
		Tag raw = tagRepo.save(new Tag("Try me raw."));
		
		Tag soupStew = tagRepo.save(new Tag ("I'm great in soups and stews."));
		
		Tag roasted = tagRepo.save(new Tag ("Roast me!"));
		
		Tag saucy = tagRepo.save(new Tag ("I'm delicious in sauces."));
		
		Tag pickled = tagRepo.save(new Tag ("Pickle me!"));
		
		Tag deepFried = tagRepo.save(new Tag ("You'll love me fried."));
		
		Tag dip = tagRepo.save(new Tag ("I make good dip"));
		
		Tag sandwich = tagRepo.save(new Tag ("Slap me on a sandwich!"));
		
		Tag salad = tagRepo.save(new Tag ("I go great in salads!"));
		
		Tag stirFry = tagRepo.save(new Tag ("Try me in a stir-fry"));
		
		Vegetable eggplant = veggieRepo.save(new Vegetable("Eggplant or Aubergine",
				"/images/aubergine.jpg","text", techFruit, soupStew, roasted, dip));
		
		Vegetable bellPepper = veggieRepo.save(new Vegetable("Bell Peppers",
				"/images/bellpepper.jpg","text", techFruit, soupStew, roasted, dip, salad, raw, saucy, stirFry, sandwich));
		
		Vegetable broccoli = veggieRepo.save(new Vegetable("Broccoli",
				"/images/broccoli.jpg","text", cruciferous, soupStew, roasted, raw, salad, stirFry));
		
		Vegetable butternut = veggieRepo.save(new Vegetable("Butternut Squash",
				"/images/butternut.jpg","text", squashMelon, soupStew, roasted, stirFry));
		
		Vegetable cabbage = veggieRepo.save(new Vegetable("Cabbage",
				"/images/cabbage.jpg","text", cruciferous, soupStew, pickled, salad, stirFry));
		
		Vegetable cucumber = veggieRepo.save(new Vegetable("Cucumber",
				"/images/cucumber.jpg","text", squashMelon, raw, salad, dip, sandwich, pickled));
		
		Vegetable delicata = veggieRepo.save(new Vegetable("Delicata Squash",
				"/images/delicata.jpg","text", squashMelon, soupStew, roasted));
		
		Vegetable hotPepper = veggieRepo.save(new Vegetable("Hot Peppers",
				"/images/hotpeppers.jpg","text", techFruit, soupStew, roasted, dip, salad, stirFry, pickled, saucy));
		
		Vegetable kohlrabi = veggieRepo.save(new Vegetable("Kohlrabi",
				"/images/kohlrabi.jpg","text", cruciferous, salad, roasted));
		
		Vegetable onion = veggieRepo.save(new Vegetable("Onion",
				"/images/onion.jpg","text", roots, soupStew, roasted, dip, deepFried, stirFry, pickled, sandwich, salad));
		
		Vegetable radish = veggieRepo.save(new Vegetable("Radish",
				"/images/radish.jpg","text", roots, soupStew, roasted, raw, pickled, salad, stirFry));
		
		Vegetable romanesco = veggieRepo.save(new Vegetable("Romanesco",
				"/images/romanesco.jpg","text", cruciferous, soupStew, roasted, raw, salad, stirFry));
		
		Vegetable santaClaus = veggieRepo.save(new Vegetable("Santa Claus Melon",
				"/images/santaclaus.jpg","text", squashMelon, raw, salad));
		
		Vegetable sweetPotato = veggieRepo.save(new Vegetable("Sweet Potato",
				"/images/sweetpotato.jpg","text", roots, soupStew, roasted, stirFry, deepFried));
		
		Vegetable tomato = veggieRepo.save(new Vegetable("Tomato",
				"/images/tomato.jpg","text", techFruit, soupStew, roasted, raw, salad, sandwich, saucy, dip));
		
		Vegetable watermelon = veggieRepo.save(new Vegetable("Watermelon",
				"/images/romanesco.jpg","text", squashMelon, raw));		
	}
	
}
