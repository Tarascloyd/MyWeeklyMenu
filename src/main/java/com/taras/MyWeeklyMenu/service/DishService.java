package com.taras.MyWeeklyMenu.service;

import java.util.List;

import com.taras.MyWeeklyMenu.entity.Dish;
import com.taras.MyWeeklyMenu.entity.Dish.Type;


public interface DishService {
	public List<Dish> findAll();
	
	public Dish findById(long theId);
	
	public void save(Dish theDish);
	
	public void deleteById(long theId);
	
	public List<Dish> searchByName(String theName);
	
	public List<Dish> searchByType(Type theType);
}
