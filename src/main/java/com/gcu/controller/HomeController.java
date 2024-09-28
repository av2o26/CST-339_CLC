package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController 
{
	/**
	 * Display the Home Page
	 * @param model
	 * @return Home View
	 */
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "Home Page");
		return "home";
	}
}
