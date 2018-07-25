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
	
	public String getImageUrl() {
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
}
