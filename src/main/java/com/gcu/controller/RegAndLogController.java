package com.gcu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.SecurityBusinessService;
import com.gcu.data.UserDataService;
import com.gcu.model.UserModel;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class RegAndLogController 
{
	// List of login data (for now)
	UserModel a = new UserModel("Alex", "Valdivia", "email@email.com", "1111111111", "user", "Password123");
	List<UserModel> userModels = new ArrayList<UserModel>(Arrays.asList(a));
	
	// Services
	@Autowired
	private SecurityBusinessService security;
	
	@Autowired
	private UserDataService userDataService; // Inject UserDataService
	
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
	public String doLogin(@Valid UserModel user, BindingResult result, Model model)
	{
		if (result.hasFieldErrors("username") || result.hasFieldErrors("password"))
		{
			model.addAttribute("title", "Login Form");
			return "login";
		}
		// Fetch the user from the database
	    UserModel foundUser  = userDataService.findUserByUsername(user.getUsername());

	    // Check if user exists and password matches
	    if (foundUser  != null && foundUser .getPassword().equals(user.getPassword())) {
	        // Successful login, redirect to shop
	        return "redirect:/shop";
	    }

	    // If login failed, return to the login page with an error message
	    model.addAttribute("title", "Login Form");
	    model.addAttribute("error", "Invalid username or password");
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
	@PostMapping("/doRegister")
	public String doRegister(@Valid UserModel user, BindingResult result, Model model) 
	{
		if(result.hasErrors()) 
		{
			model.addAttribute("title", "Registration Form");
			return "register";
		}
		
		 // Check if the username already exists
		if (security.doesUsernameExist(userModels, user))
		{
			// If the username existed, make the user retry
			model.addAttribute("title", "Registration Form");
			model.addAttribute("error", "Username already exists");
			return "register";
		}
		
		// Call createUser  to save the new user to the database
        boolean isCreated = userDataService.createUser (user);
        
        if (isCreated) {
            // Redirect to the login page if registration is successful
            return "redirect:/login";
        } else {
            // If registration failed, show an error message
            model.addAttribute("title", "Registration Form");
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
	}
}
