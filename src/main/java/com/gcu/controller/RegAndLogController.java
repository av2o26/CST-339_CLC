package com.gcu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.UserModel;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class RegAndLogController 
{
	// List of login data (for now)
	UserModel a = new UserModel("Alex", "Valdivia", "email@email.com", "1111111111", "user", "Password123");
	List<UserModel> userModels = new ArrayList<UserModel>(Arrays.asList(a));
	
	/**
	 * Display the Login Page
	 * @param model
	 * @return Login View
	 */
	@GetMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("title", "Login Form");
		model.addAttribute("userModel", new UserModel());
		return "login";
	}
	
	/**
	 * Check login credentials
	 * @param userModel
	 * @param model
	 * @return Shop View if successful, login View if unsuccessful
	 */
	@PostMapping("/doLogin")
	public String doLogin(UserModel user, Model model)
	{
		// Check for existing username and password combination
		for (int i = 0; i < userModels.size(); i++)
		{
			if(userModels.get(i).getUsername().equals(user.getUsername()))
			{
				
				if (userModels.get(i).getPassword().equals(user.getPassword()))
				{
					// If username and password matched an account, log into shop view
					return "shop";
				}
			}
		}
		
		// If login failed, login page gets returned
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
		model.addAttribute("userModel", new UserModel());
		return "register";
	}
	
	/**
     * Handle registration form submission
     * @param userModel
     * @param model
     * @return Login View if registration is successful, Registration View if unsuccessful
     */
	@PostMapping("/register")
	public String doRegister(@Valid UserModel user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Registration Form");
			return "register";
		}
		
		 // Check if the username already exists
		for(UserModel existingUser : userModels) {
			if(existingUser.getUsername().equals(user.getUsername()))
			{
				model.addAttribute("title", "Registration Form");
				model.addAttribute("error", "Username already exists");
				return "register";
			}
		}
		
		// Add the new user to the list of registered users
		userModels.add(user);
		
		// Redirect to the login page
		return "redirect:/login";
	}
}
