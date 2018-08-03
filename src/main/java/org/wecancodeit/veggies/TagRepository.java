package org.wecancodeit.veggies;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Optional<Tag> findFirstByName(String tagName);

	Collection<Tag> findByVeggiesId(Optional<Vegetable> vegOptional);
	
	Collection<Tag> findByVeggiesContains(Vegetable veggie);

}
