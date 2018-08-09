package org.wecancodeit.veggies;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProducePopulator implements CommandLineRunner {

	@Resource
	private VeggieRepository veggieRepo;
	
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
		
		Veggie eggplant = veggieRepo.save(new Veggie("Eggplant or Aubergine",
				"/images/aubergine.jpg","text", techFruit, soupStew, roasted, dip));
		
		Veggie bellPepper = veggieRepo.save(new Veggie("Bell Peppers",
				"/images/bellpepper.jpg","text", techFruit, soupStew, roasted, dip, salad, raw, saucy, stirFry, sandwich));
		
		Veggie broccoli = veggieRepo.save(new Veggie("Broccoli",
				"/images/broccoli.jpg","text", cruciferous, soupStew, roasted, raw, salad, stirFry));
		
		Veggie butternut = veggieRepo.save(new Veggie("Butternut Squash",
				"/images/butternut.jpg","text", squashMelon, soupStew, roasted, stirFry));
		
		Veggie cabbage = veggieRepo.save(new Veggie("Cabbage",
				"/images/cabbage.jpg","text", cruciferous, soupStew, pickled, salad, stirFry));
		
		Veggie cucumber = veggieRepo.save(new Veggie("Cucumber",
				"/images/cucumber.jpg","text", squashMelon, raw, salad, dip, sandwich, pickled));
		
		Veggie delicata = veggieRepo.save(new Veggie("Delicata Squash",
				"/images/delicata.jpg","text", squashMelon, soupStew, roasted));
		
		Veggie hotPepper = veggieRepo.save(new Veggie("Hot Peppers",
				"/images/hotpeppers.jpg","text", techFruit, soupStew, roasted, dip, salad, stirFry, pickled, saucy));
		
		Veggie kohlrabi = veggieRepo.save(new Veggie("Kohlrabi",
				"/images/kohlrabi.jpg","text", cruciferous, salad, roasted));
		
		Veggie onion = veggieRepo.save(new Veggie("Onion",
				"/images/onion.jpg","text", roots, soupStew, roasted, dip, deepFried, stirFry, pickled, sandwich, salad));
		
		Veggie radish = veggieRepo.save(new Veggie("Radish",
				"/images/radish.jpg","text", roots, soupStew, roasted, raw, pickled, salad, stirFry));
		
		Veggie romanesco = veggieRepo.save(new Veggie("Romanesco",
				"/images/romanesco.jpg","text", cruciferous, soupStew, roasted, raw, salad, stirFry));
		
		Veggie santaClaus = veggieRepo.save(new Veggie("Santa Claus Melon",
				"/images/santaclaus.jpg","text", squashMelon, raw, salad));
		
		Veggie sweetPotato = veggieRepo.save(new Veggie("Sweet Potato",
				"/images/sweetpotato.jpg","text", roots, soupStew, roasted, stirFry, deepFried));
		
		Veggie tomato = veggieRepo.save(new Veggie("Tomato",
				"/images/tomato.jpg","text", techFruit, soupStew, roasted, raw, salad, sandwich, saucy, dip));
		
		Veggie watermelon = veggieRepo.save(new Veggie("Watermelon",
				"/images/romanesco.jpg","text", squashMelon, raw));		
	}
	
}
