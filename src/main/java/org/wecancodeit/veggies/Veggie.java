package org.wecancodeit.veggies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Veggie {

	@Id
	@GeneratedValue
	private long id;
	
	private String veggieName;
	private String imgUrl;
	
	@Lob
	private String text;
	
	@ManyToOne
	private Category category;

	private String categoryName;
	
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
		return categoryName;
	}
	
	protected Veggie() {
		
	}
	
	public Veggie(String veggieName, String imgUrl, String text, Category category) {
		this.veggieName = veggieName;
		this.imgUrl = imgUrl;
		this.text = text;
		this.category = category;
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
		Veggie other = (Veggie) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
