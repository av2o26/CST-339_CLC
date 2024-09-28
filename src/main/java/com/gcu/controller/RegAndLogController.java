package com.gcu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.UserModel;


@Controller
@RequestMapping("/login")
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
	@GetMapping("/")
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
}
