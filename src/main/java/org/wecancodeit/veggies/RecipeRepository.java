package org.wecancodeit.veggies;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Optional<Recipe> findByRecipeName(String recipeName);

	Collection<Recipe> findByVeggie(Veggie veggie);

}
