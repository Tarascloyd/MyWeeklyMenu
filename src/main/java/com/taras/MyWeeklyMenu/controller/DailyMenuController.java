package com.taras.MyWeeklyMenu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taras.MyWeeklyMenu.entity.DailyMenu;
import com.taras.MyWeeklyMenu.service.DailyMenuService;
import com.taras.MyWeeklyMenu.service.DishService;

@Controller
@RequestMapping("/daily")
public class DailyMenuController {
	private DailyMenuService dailyMenuService;
	private DishService dishService;

	public DailyMenuController(DailyMenuService theDailyMenuService, DishService theDishService) {
		dailyMenuService = theDailyMenuService;
		dishService = theDishService;
	}
	
	@GetMapping("/list")
	public String listDailyMenues(Model theModel) {
		
		// get dishes from db
		List<DailyMenu> theDailyMenues = dailyMenuService.findAll();
		
		// add to the spring model
		theModel.addAttribute("daily", theDailyMenues);
		
		return "daily/list-menues";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("dailyMenu", new DailyMenu());
		model.addAttribute("dishes", dishService.findAll());
		return "daily/add";
	}
	
	@PostMapping("/save")
	public String saveDailyMenu(
			@ModelAttribute("dailyMenu") @Valid DailyMenu theDailyMenu,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "daily/add";
		}
		else {		
			// save the day
			dailyMenuService.save(theDailyMenu);
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/daily/list";
		}
	}
	
}
