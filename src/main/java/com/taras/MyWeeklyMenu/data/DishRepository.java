package com.taras.MyWeeklyMenu.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taras.MyWeeklyMenu.entity.Dish;
import com.taras.MyWeeklyMenu.entity.Dish.Type;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long>{
	List<Dish> findAllByOrderByNameAsc();
	List<Dish> findByNameContainsAllIgnoreCase(String theName);
	List<Dish> findByTypeIs(Type theType);
}
