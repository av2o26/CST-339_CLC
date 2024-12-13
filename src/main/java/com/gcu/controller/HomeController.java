package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{
	/**
	 * Display the home page
	 * @param model
	 * @return home view
	 */
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "My Home");
		return "home";
	}
}
