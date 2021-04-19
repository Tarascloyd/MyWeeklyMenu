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
import org.springframework.web.bind.annotation.RequestParam;

import com.taras.MyWeeklyMenu.entity.Dish;
import com.taras.MyWeeklyMenu.service.DishService;

@Controller
@RequestMapping("/dishes")
public class DishController {
	private DishService dishService;

	public DishController(DishService theDishService) {
		dishService = theDishService;
	}
	
	@GetMapping("/list")
	public String listDishes(Model theModel) {
		
		// get dishes from db
		List<Dish> theDishes = dishService.findAll();
		
		// add to the spring model
		theModel.addAttribute("dishes", theDishes);
		
		return "dishes/list-dishes";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("dish", new Dish());
		model.addAttribute("types", Dish.Type.values());
		return "dishes/add";
	}
	
	@PostMapping("/save")
	public String saveDish(
			@ModelAttribute("dish") @Valid Dish theDish,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("types", Dish.Type.values());
			return "dishes/add";
		}
		else {		
			// save the dish
			dishService.save(theDish);
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/dishes/list";
		}
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("dishId") int theId,
									Model theModel) {
		
		// get the dish from the service
		Dish theDish = dishService.findById(theId);
		
		// set player as a model attribute to pre-populate the form
		theModel.addAttribute("dish", theDish);
		theModel.addAttribute("types", Dish.Type.values());
		
		// send over to our form
		return "dishes/add";			
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("dishId") int theId) {
		
		// delete the player
		dishService.deleteById(theId);
		
		// redirect to /players/list
		return "redirect:/dishes/list";
		
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("name") String theName, Model theModel) {
		
		// check name, if empty then just give list of all dishes

		if (theName.trim().isEmpty()) {
			return "redirect:/dishes/list";
		}
		else {
			// else, search by name
			List<Dish> theDishes =
					dishService.searchByName(theName);
			
			// add to the spring model
			theModel.addAttribute("dishes", theDishes);
			
			// send to list-employees
			return "dishes/list-dishes";
		}
		
	}
}
