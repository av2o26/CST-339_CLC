package com.gcu.business;

import java.util.List;

import com.gcu.model.ProductModel;

public interface ProductsBusinessServiceInterface 
{
	public List<ProductModel> getProducts();
	
	public ProductModel getProductById(String id);
	
	public List<ProductModel> addProduct(ProductModel product);
	
	public List<ProductModel> updateProduct(ProductModel product);
	
	public List<ProductModel> deleteProduct(ProductModel product);
	
	public void init();
	
	public void destroy();
}
