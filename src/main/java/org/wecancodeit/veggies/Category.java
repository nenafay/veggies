package org.wecancodeit.veggies;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String categoryName;
	private String imgUrl;
	private String blurb;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private Collection<Veggie> veggies;

	public long getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public String getBlurb() {
		return blurb;
	}
	
	public Collection<Veggie> getVeggies() {
		return veggies;
	}
	
	protected Category() {
		
	}

	public Category(String categoryName, String imgUrl, String blurb) {
		this.categoryName = categoryName;
		this.imgUrl = imgUrl;
		this.blurb = blurb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
