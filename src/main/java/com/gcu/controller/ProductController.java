package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.ProductModel;

@Controller 
@RequestMapping("/")
public class ProductController {
	
	@GetMapping("/shop")
	public String login(Model model)
	{
		model.addAttribute("title", "Shop");
		model.addAttribute("productModel", new ProductModel());
		return "shop";
	}
	
	@PostMapping("/createProduct")
    public String createProduct(ProductModel productModel, BindingResult bindingResult, Model model) {
        // Validate the productModel if needed
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the shop view with the current productModel
            model.addAttribute("title", "Shop");
            return "shop"; // Return to the shop view with errors
        }
        
        // TODO: add method to store data to a database

        // Optionally, you can add a success message or redirect to another page
        model.addAttribute("title", "Shop");
        model.addAttribute("productModel", new ProductModel()); // Reset the form
        model.addAttribute("successMessage", "Product created successfully!");

        return "shop"; // Return to the shop view
	}


}
