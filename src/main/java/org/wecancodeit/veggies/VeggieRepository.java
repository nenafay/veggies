package org.wecancodeit.veggies;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface VeggieRepository extends CrudRepository<Veggie, Long> {

	Collection<Veggie> findByCategory(Category category);
	
	Collection<Veggie> findByCategoryId(long id);

	Collection<Veggie> findByTagsContains(Tag tag);

	Veggie findByVeggieName(String veggieName);

	Optional<Veggie> findByVeggieId(long veggieId);

}
