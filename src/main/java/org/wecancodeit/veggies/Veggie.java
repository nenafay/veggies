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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Veggie {

	@Id
	@GeneratedValue
	private long veggieId;
	
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
	@OneToMany(mappedBy = "veggie")
	private Collection<Recipe> recipes;
	
	public long getId() {
		return veggieId;
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
	
	public Category getCategory() {
		return category;
	}
	
	public Collection<Tag> getTags() {
		return tags;	
	}
	
	public Collection<Recipe> getRecipes(){
		return recipes;
	}
	
	protected Veggie() {	
	}
	
	public Veggie(String veggieName, String imgUrl, String text, Category category) {
		this.veggieName = veggieName;
		this.imgUrl = imgUrl;
		this.text = text;
		this.category = category;
	}
	
	public Veggie(String veggieName, String imgUrl, String text, Category category, Tag...tags) {
		this.veggieName = veggieName;
		this.imgUrl = imgUrl;
		this.text = text;
		this.category = category;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public void deleteTag(Tag tag) {
		this.tags.remove(tag);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (veggieId ^ (veggieId >>> 32));
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
		Veggie other = (Veggie) obj;
		if (veggieId != other.veggieId)
			return false;
		return true;
	}
}
