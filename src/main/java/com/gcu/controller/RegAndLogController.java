package com.gcu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.data.ProductsDataService;
import com.gcu.data.UsersDataService;
import com.gcu.data.entity.UserEntity;

import jakarta.validation.Valid;


@Controller
public class RegAndLogController
{
	@Autowired
	UsersDataService usersDataService;
	
	/**
	 * Display the Login Page
	 * @param model
	 * @return Login View
	 */
	@GetMapping("/login")
	public String display(Model model)
	{
		model.addAttribute("title", "Login Form");
		return "login";
	}
	
	/**
     * Display the Registration Page
     * @param model
     * @return Registration View
     */
	@GetMapping("/register")
	public String register(Model model)
	{
		model.addAttribute("title", "Registration Form");
		return "register";
	}
	
	/**
     * Handle registration form submission
     * @param userModel
     * @param model
     * @return Login View if registration is successful, Registration View if unsuccessful
     */
	@PostMapping("/doRegister")
	public String doRegister(UserEntity user, Model model) 
	{        
        if (usersDataService.createUser(user)) {
            // Redirect to the login page if registration is successful
            return "login";
        } else {
            // If registration failed, show an error message
            model.addAttribute("title", "Registration Form");
            model.addAttribute("error", "Registration failed. Please try again.");
            return "login";
        }
	}
}
