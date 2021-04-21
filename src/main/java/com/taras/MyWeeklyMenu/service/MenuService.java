package com.taras.MyWeeklyMenu.service;

import java.util.List;

import com.taras.MyWeeklyMenu.entity.Menu;

public interface MenuService {
	public List<Menu> findAll();
	
	public Menu findById(long theId);
	
	public void save(Menu theDailyMenu);
	
	public void deleteById(long theId);
}
