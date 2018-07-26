package org.wecancodeit.veggies;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vegetable {

	@Id
	@GeneratedValue
	private long id;
	
	private String veggieName;
	private String imgUrl;
	
	@Lob
	private String text;
	
	@ManyToOne
	private Category category;

	@JsonIgnore
	@ManyToMany
	private Collection<Tag> tags;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "veggies")
	private Collection<Recipe> recipes;
	
	public long getId() {
		return id;
	}
	
	public String getVeggieName() {
		return veggieName;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public String getText() {
		return text;
	}
	
	public String getCategoryName() {
		return category.getCategoryName();
	}
	
	public Collection<Tag> getTags() {
		return tags;	
	}
	
	public Collection<Recipe> getRecipes(){
		return recipes;
	}
	
	protected Vegetable() {	
	}
	
	public Vegetable(String veggieName, String imgUrl, String text, Category category) {
		this.veggieName = veggieName;
		this.imgUrl = imgUrl;
		this.text = text;
		this.category = category;
	}
	
	public Vegetable(String veggieName, String imgUrl, String text, Category category, Tag...tags) {
		this.veggieName = veggieName;
		this.imgUrl = imgUrl;
		this.text = text;
		this.category = category;
		this.tags = new HashSet<>(Arrays.asList(tags));
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
		Vegetable other = (Vegetable) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
