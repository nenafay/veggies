package org.wecancodeit.veggies;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String recipeTitle ;
	private String userName;
	
	@Lob
	private String recipeText;
	
	@ManyToMany
	private Collection<Vegetable> veggies;
	
	public long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getRecipeTitle() {
		return recipeTitle;
	}
	
	public String getRecipeText() {
		return recipeText;
	}
	
	public Collection<Vegetable> getVeggies(){
		return veggies;
	}
	
	protected Recipe() {
		
	}

	public Recipe(String userName, String recipeTitle, String recipeText, Vegetable...veggies) {
		this.userName = userName;
		this.recipeTitle = recipeTitle;
		this.recipeText = recipeText;
		this.veggies = new HashSet<>(Arrays.asList(veggies));
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
		Recipe other = (Recipe) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
