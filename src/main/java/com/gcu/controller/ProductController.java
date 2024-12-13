package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	/**
	 * Display the shop view
	 * @param model
	 * @return shop view
	 */
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
	
	/**
	 * Display the product update view
	 * @param model
	 * @return edit view
	 */
	@GetMapping("/edit")
	public String editProducts(Model model)
	{
		
		
		model.addAttribute("title", "Edit Shop");
		model.addAttribute("productModel", new ProductModel());
		
		return "EditProduct";
	}
	
	/**
	 * Display the product creation view
	 * @param model
	 * @return create view
	 */
	@GetMapping("/create")
	public String createProducts(Model model)
	{
		
		
		model.addAttribute("title", "Edit Shop");
		model.addAttribute("productModel", new ProductModel());
		
		return "CreateProduct";
	}
	
	/**
	 * Display the product deletion view
	 * @param model
	 * @return delete view
	 */
	@GetMapping("/delete")
	public String deleteProducts(Model model)
	{
		
		
		model.addAttribute("title", "Edit Shop");
		model.addAttribute("productModel", new ProductModel());
		
		return "DeleteProduct";
	}
	
	/**
	 * Send the request to create a product
	 * @param productModel
	 * @return shop view
	 */
	@PostMapping("/createProduct")
    public String createProduct(ProductModel productModel) 
	{
        service.addProduct(productModel);
        
        return "redirect:/shop/display";
       }
	
	/**
	 * Send the request to update a product
	 * @param productModel
	 * @return shop view
	 */
	@PostMapping("/updateProduct")
	public String updateProduct(ProductModel productModel)
	{
		service.updateProduct(productModel);
		
		return "redirect:/shop/display";
	}
	
	/**
	 * Send the request to delete a product
	 * @param productModel
	 * @return shop view
	 */
	@PostMapping("/deleteProduct")
	public String deleteProduct(@ModelAttribute ProductModel productModel) {
	    // Call the service method with the ID of the product to delete
	    service.deleteProductById(productModel.getId());
	    
	    return "redirect:/shop/display"; // Redirect to display the updated product list
	}
}
