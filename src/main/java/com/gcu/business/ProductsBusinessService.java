package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.ProductModel;

public class ProductsBusinessService implements ProductsBusinessServiceInterface
{
	// List of Products
	List<ProductModel> products = new ArrayList<ProductModel>();
	
	@Override
	public List<ProductModel> getProducts() 
	{
		products.add(new ProductModel("Stick", 0.50, "It's just a stick.", 10, 1));
		
		return products;
	}

	@Override
	public List<ProductModel> addProduct(ProductModel product) 
	{
		products.add(product);
		
		return products;
	}

	@Override
	public void init() 
	{
		System.out.println("Init has initialized.");
	}

	@Override
	public void destroy() 
	{
		System.out.println("Destroy has destroyed.");
	}
}
