package com.taras.MyWeeklyMenu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.MyWeeklyMenu.data.MenuRepository;
import com.taras.MyWeeklyMenu.entity.Menu;

@Service
public class MenuServiceImpl implements MenuService {
	private MenuRepository menuRepository;
	
	@Autowired
	public MenuServiceImpl(MenuRepository theMenuRepository) {
		menuRepository = theMenuRepository;
	}
	
	@Override
	public List<Menu> findAll() {
		return (List<Menu>) menuRepository.findAll();
	}

	@Override
	public Menu findById(long theId) {
		Optional<Menu> result = menuRepository.findById(theId);
		
		Menu theMenu = null;
		
		if (result.isPresent()) {
			theMenu = result.get();
		}
		else {
			// we didn't find the dailyMenu
			throw new RuntimeException("Did not find dailyMenu id - " + theId);
		}
		
		return theMenu;
	}

	@Override
	public void save(Menu theMenu) {
		menuRepository.save(theMenu);

	}

	@Override
	public void deleteById(long theId) {
		menuRepository.deleteById(theId);

	}

}
