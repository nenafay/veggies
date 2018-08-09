package org.wecancodeit.veggies;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	private long tagId;
	
	private String tagName;
	
	@ManyToMany(mappedBy="tags")
	private Collection<Veggie> veggies;
	
	public long getId() {
		return tagId;
	}
			
	public String getTagName() {
	return tagName;
	}
	
	protected Tag() {
	}

	public Tag(String tagName) {
		this.tagName = tagName;
	}

	public Collection<Veggie> getVeggies() {
		return veggies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (tagId ^ (tagId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tagId != other.tagId)
			return false;
		return true;
	}

	
}
