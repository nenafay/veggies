package org.wecancodeit.veggies;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface VegetableRepository extends CrudRepository<Veggie, Long> {

	Collection<Veggie> findByCategory(Category category);
	
	Collection<Veggie> findByCategoryId(long id);

}
