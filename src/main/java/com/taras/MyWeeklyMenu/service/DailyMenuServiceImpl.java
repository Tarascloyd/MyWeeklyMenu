package com.taras.MyWeeklyMenu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.MyWeeklyMenu.data.DailyMenuRepository;
import com.taras.MyWeeklyMenu.entity.DailyMenu;

@Service
public class DailyMenuServiceImpl implements DailyMenuService {
	private DailyMenuRepository dailyMenuRepository;
	
	@Autowired
	public DailyMenuServiceImpl(DailyMenuRepository theDailyMenuRepository) {
		dailyMenuRepository = theDailyMenuRepository;
	}
	
	@Override
	public List<DailyMenu> findAll() {
		return (List<DailyMenu>) dailyMenuRepository.findAll();
	}

	@Override
	public DailyMenu findById(long theId) {
		Optional<DailyMenu> result = dailyMenuRepository.findById(theId);
		
		DailyMenu theDailyMenu = null;
		
		if (result.isPresent()) {
			theDailyMenu = result.get();
		}
		else {
			// we didn't find the dailyMenu
			throw new RuntimeException("Did not find dailyMenu id - " + theId);
		}
		
		return theDailyMenu;
	}

	@Override
	public void save(DailyMenu theDailyMenu) {
		dailyMenuRepository.save(theDailyMenu);

	}

	@Override
	public void deleteById(long theId) {
		dailyMenuRepository.deleteById(theId);

	}

}
