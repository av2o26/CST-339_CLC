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
    public String createProduct(ProductModel productModel) 
	{
        // Add new product to list
        products = service.addProduct(productModel);
        
        
        return "redirect:/shop";
       }
	
	
	@PostMapping("/updateProduct")
	public String updateProduct(ProductModel productModel)
	{
		products = service.updateProduct(productModel);
		
		return "redirect:/shop";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(ProductModel productModel) 
	{
		products = service.deleteProduct(productModel);
		
		return "redirect:/shop";
	}
}
