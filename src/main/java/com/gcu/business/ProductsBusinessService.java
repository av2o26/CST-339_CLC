package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterfaceProduct;
import com.gcu.model.ProductModel;

public class ProductsBusinessService implements ProductsBusinessServiceInterface
{
	@Autowired
	public DataAccessInterfaceProduct<ProductModel> service;
	// List of Products
	List<ProductModel> products = new ArrayList<ProductModel>();
	
	@Override
	public List<ProductModel> getProducts() 
	{
		return service.findAll();
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
