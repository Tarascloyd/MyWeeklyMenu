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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.taras.MyWeeklyMenu.entity.DailyMenu;
import com.taras.MyWeeklyMenu.entity.Menu;
import com.taras.MyWeeklyMenu.service.DailyMenuService;
import com.taras.MyWeeklyMenu.service.MenuService;

@Controller
@RequestMapping("/menu")
@SessionAttributes("menu")
public class MenuController {
	private MenuService menuService;
	private DailyMenuService dailyMenuService;

	public MenuController(MenuService theMenuService, DailyMenuService theDailyMenuService) {
		menuService = theMenuService;
		dailyMenuService = theDailyMenuService;
	}
	
	@GetMapping("/list")
	public String listDishes(Model theModel) {
		
		// get menues from db
		List<Menu> theMenues = menuService.findAll();
		
		// add to the spring model
		theModel.addAttribute("menues", theMenues);
		return "menu/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		Menu theMenu = new Menu();
		theMenu.setName("Текущее меню");
		menuService.save(theMenu);
		for (int i=0; i<3; i++) {
			DailyMenu theDailyMenu = new DailyMenu();
			theDailyMenu.setMenu(theMenu);
			switch (i) {
				case 0:
					theDailyMenu.setName("Понедельник");
					break;
				case 1:
					theDailyMenu.setName("Вторник");
					break;
				case 2:
					theDailyMenu.setName("Среда");
					break;
			}
			
			dailyMenuService.save(theDailyMenu);
			theMenu.getDays().add(theDailyMenu);
		}
		menuService.save(theMenu);
		model.addAttribute("menu", theMenu);
		return "menu/addMenu";
	}
	
	@PostMapping("/save")
	public String saveDish(
			@ModelAttribute @Valid Menu menu,
			BindingResult bindingResult, SessionStatus sessionStatus) {
		
		if (bindingResult.hasErrors()) {
			return "menu/add";
		}
		else {		
			// save the menu
			menuService.save(menu);
			sessionStatus.setComplete();
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/menu/list";
		}
	}

	@GetMapping("/update")
	public String update(@RequestParam("menuId") int theId,
							Model theModel) {
		
		// get the menu from the service
		Menu theMenu = menuService.findById(theId);
		
		// set menu as a model attribute to pre-populate the form
		theModel.addAttribute("menu", theMenu);
		
		// send over to our form
		return "menu/addMenu";			
	}
	
	@GetMapping("/back")
	public String backToMenu() {
	
		return "menu/addMenu";			
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("menuId") int theId) {
		
		// delete the player
		menuService.deleteById(theId);
		
		// redirect to /players/list
		return "redirect:/menu/list";
		
	}
	
}
