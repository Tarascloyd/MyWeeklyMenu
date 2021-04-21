package com.taras.MyWeeklyMenu.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taras.MyWeeklyMenu.entity.DailyMenu;
import com.taras.MyWeeklyMenu.entity.Dish;
import com.taras.MyWeeklyMenu.entity.Dish.Type;
import com.taras.MyWeeklyMenu.service.DailyMenuService;
import com.taras.MyWeeklyMenu.service.DishService;

@Controller
@RequestMapping("/daily")
@SessionAttributes("menu")
public class DailyMenuController {
	private DailyMenuService dailyMenuService;
	private DishService dishService;

	public DailyMenuController(DailyMenuService theDailyMenuService, DishService theDishService) {
		dailyMenuService = theDailyMenuService;
		dishService = theDishService;
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("dayId") int theId, Model model) {
		model.addAttribute("dailyMenu", dailyMenuService.findById(theId));
		
		List<Dish> theDishes = dishService.findAll();
		Type[] types = Dish.Type.values();
		for (Type type : types) {
		      model.addAttribute(type.toString().toLowerCase(),
		          filterByType(theDishes, type));
		}
		
		return "daily/update";
	}
	
	@PostMapping("/save")
	public String saveDailyMenu(
			@ModelAttribute @Valid DailyMenu theDailyMenu, 
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "daily/update";
		}
		else {		
			// save the day
			System.out.println(theDailyMenu.getId() + " " + theDailyMenu.getName());
			for (Dish d : theDailyMenu.getBreakfast()) {
				System.out.println(d.getName());
			}
			dailyMenuService.save(theDailyMenu);
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/menu/back";
		}
	};
	 private List<Dish> filterByType(
		      List<Dish> dishes, Type type) {
		    return dishes
		              .stream()
		              .filter(x -> x.getType().equals(type))
		              .collect(Collectors.toList());
	 }
}
