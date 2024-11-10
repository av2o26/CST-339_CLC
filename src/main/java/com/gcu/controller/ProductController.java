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
		products.clear();
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
        
        // Reload shop view
        model.addAttribute("title", "Shop");
        model.addAttribute("products", products); // Reset the form
        model.addAttribute("successMessage", "Product created successfully!");

        return "shop"; // Return to the shop view
	}
	
	/* TODO: The views need to be finished for the updating and deleting. I thought about adding onto
	 * 		 the editShop view and making one big page where all the edits could be done, or
	 * 		 alternatively making multiple different views for each action. I'll let you decide, I've
	 * 		 deleted my attempts since none of them got close to working and have left these blank for now
	 * 		 since they rely on how the views work to code these out. */
	public String updateProduct()
	{
		return "";
	}

	public String deleteProduct() 
	{
		return "";
	}
}
