package org.wecancodeit.veggies;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface VegetableRepository extends CrudRepository<Vegetable, Long> {

	Collection<Vegetable> findByCategory(Category category);
	
	Collection<Vegetable> findByCategoryId(long id);

	Collection<Vegetable> findByTagsId(long tagId);

}
