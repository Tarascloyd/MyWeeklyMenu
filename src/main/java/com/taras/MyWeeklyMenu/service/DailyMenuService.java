package com.taras.MyWeeklyMenu.service;

import java.util.List;

import com.taras.MyWeeklyMenu.entity.DailyMenu;

public interface DailyMenuService {
	public List<DailyMenu> findAll();
	
	public DailyMenu findById(long theId);
	
	public void save(DailyMenu theDailyMenu);
	
	public void deleteById(long theId);
}
