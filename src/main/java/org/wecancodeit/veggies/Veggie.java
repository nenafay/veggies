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

}
