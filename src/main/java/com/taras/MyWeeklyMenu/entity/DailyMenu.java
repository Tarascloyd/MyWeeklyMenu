package com.taras.MyWeeklyMenu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DailyMenu {
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		@ManyToMany(targetEntity=Dish.class)
		private List<Dish> breakfast = new ArrayList<>();
		@ManyToMany(targetEntity=Dish.class)
	  	private List<Dish> lunch = new ArrayList<>();
		@ManyToMany(targetEntity=Dish.class)
	  	private List<Dish> dinner = new ArrayList<>();
	  	@ManyToOne
	  	private Menu menu;
	  	
	  	private String name;
	  	
	  	public DailyMenu() {
		     
	  	}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public List<Dish> getBreakfast() {
			return breakfast;
		}
		public void setBreakfast(List<Dish> breakfast) {
			this.breakfast = breakfast;
		}
		
		public List<Dish> getLunch() {
			return lunch;
		}

		public void setLunch(List<Dish> lunch) {
			this.lunch = lunch;
		}

		public List<Dish> getDinner() {
			return dinner;
		}
		public void setDinner(List<Dish> dinner) {
			this.dinner = dinner;
		}

		public Menu getMenu() {
			return menu;
		}

		public void setMenu(Menu menu) {
			this.menu = menu;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	  	
}
