package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.ProductsBusinessServiceInterface;
import com.gcu.model.ProductModel;

@Controller 
@RequestMapping("/")
public class ProductController 
{	
	@Autowired
	private ProductsBusinessServiceInterface service;
	
	// Product List
	private List<ProductModel> products;
	
	@GetMapping("/shop")
	public String products(Model model)
	{
		// Fill product list
		products = service.getProducts();
		
		model.addAttribute("title", "Shop");
		model.addAttribute("products", products);
		return "shop";
	}
	
	@GetMapping("/editProducts")
	public String editProducts(Model model)
	{
		model.addAttribute("title", "Edit Shop");
		model.addAttribute("productModel", new ProductModel());
		
		return "editShop";
	}
	
	@PostMapping("/createProduct")
    public String createProduct(ProductModel productModel, BindingResult bindingResult, Model model) 
	{
        // Validate the productModel if needed
        if (bindingResult.hasErrors()) 
        {
            // If there are validation errors, return to the shop view with the current productModel
            model.addAttribute("title", "Shop");
            return "shop"; // Return to the shop view with errors
        }
        
        // Add new product to list
        products = service.addProduct(productModel);
        
        // TODO: add method to store data to a database

        // Optionally, you can add a success message or redirect to another page
        model.addAttribute("title", "Shop");
        model.addAttribute("products", products); // Reset the form
        model.addAttribute("successMessage", "Product created successfully!");

        return "shop"; // Return to the shop view
	}
}
