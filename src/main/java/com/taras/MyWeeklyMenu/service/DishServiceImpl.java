package com.taras.MyWeeklyMenu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.MyWeeklyMenu.data.DishRepository;
import com.taras.MyWeeklyMenu.entity.Dish;
import com.taras.MyWeeklyMenu.entity.Dish.Type;

@Service
public class DishServiceImpl implements DishService {

	private DishRepository dishRepository;
	
	@Autowired
	public DishServiceImpl(DishRepository theDishRepository) {
		dishRepository = theDishRepository;
	}
	
	@Override
	public List<Dish> findAll() {
		return (List<Dish>) dishRepository.findAllByOrderByNameAsc();
	}

	@Override
	public Dish findById(long theId) {
		Optional<Dish> result = dishRepository.findById(theId);
		
		Dish theDish = null;
		
		if (result.isPresent()) {
			theDish = result.get();
		}
		else {
			// we didn't find the dish
			throw new RuntimeException("Did not find dish id - " + theId);
		}
		
		return theDish;
	}

	@Override
	public void save(Dish theDish) {
		dishRepository.save(theDish);
	}

	@Override
	public void deleteById(long theId) {
		dishRepository.deleteById(theId);

	}

	@Override
	public List<Dish> searchByName(String theName) {
		return dishRepository.findByNameContainsAllIgnoreCase(theName);
	}

	@Override
	public List<Dish> searchByType(Type theType) {
		return dishRepository.findByTypeIs(theType);
	}

}
