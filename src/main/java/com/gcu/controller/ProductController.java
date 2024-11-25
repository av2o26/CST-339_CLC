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
@RequestMapping("/shop")
public class ProductController 
{	
	@Autowired
	private ProductsBusinessServiceInterface service;
	
	@GetMapping("/display")
	public String display(Model model)
	{		
		// Get products
		List<ProductModel> products = service.getProducts();
		
		// Show product view
		model.addAttribute("title", "My Orders");
		model.addAttribute("products", products);
		return "shop";
	}
	
	@GetMapping("/edit")
	public String editProducts(Model model)
	{
		// TODO: Maybe needs to be redone?
		
		model.addAttribute("title", "Edit Shop");
		model.addAttribute("productModel", new ProductModel());
		
		return "editShop";
	}
	
	@PostMapping("/createProduct")
    public String createProduct(ProductModel productModel) 
	{
        service.addProduct(productModel);
        
        return "shop";
       }
	
	
	@PostMapping("/updateProduct")
	public String updateProduct(ProductModel productModel)
	{
		service.updateProduct(productModel);
		
		return "shop";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(ProductModel productModel) 
	{
		service.deleteProduct(productModel);
		
		return "shop";
	}
}
