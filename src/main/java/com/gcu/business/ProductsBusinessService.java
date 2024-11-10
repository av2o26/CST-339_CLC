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
	
	@Override
	public List<ProductModel> getProducts() 
	{
		return service.findAll();
	}

	@Override
	public List<ProductModel> addProduct(ProductModel product) 
	{
		boolean isCreated = service.create(product);
	    List<ProductModel> productList = new ArrayList<>();

	    if (isCreated)
	        productList.add(product); // Add the product to the list if created successfully

	    return productList;
	}
	
	@Override
	public List<ProductModel> updateProduct(ProductModel product)
	{
		service.update(product);
		
		return service.findAll();
	}
	
	@Override
	public List<ProductModel> deleteProduct(ProductModel product)
	{
		service.delete(product);
		
		return service.findAll();
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
