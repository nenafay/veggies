package org.wecancodeit.veggies;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Optional<Tag> findFirstByTagName(String tagName);

	Collection<Tag> findByVeggiesId(Optional<Vegetable> vegOptional);
	
	Collection<Tag> findByVeggiesContains(Vegetable veggie);

	Optional<Tag> findByTagName(String tagName);

}
