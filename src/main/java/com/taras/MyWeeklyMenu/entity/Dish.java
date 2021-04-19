package com.taras.MyWeeklyMenu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Dish {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=3, message="Name must be at least 5 characters long")
	private String name;
	private Type type;
	
	public static enum Type {
		SOUP, MAIN, SALAD
	}
	
	public Dish() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
}
