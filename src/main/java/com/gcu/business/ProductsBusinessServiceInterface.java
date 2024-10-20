package com.gcu.business;

import java.util.List;

import com.gcu.model.ProductModel;

public interface ProductsBusinessServiceInterface 
{
	/**
	 * See what products are in the shop
	 * @return products list
	 */
	public List<ProductModel> getProducts();
	
	/**
	 * Adds a product to the shop
	 * @param product
	 * @return updated products list
	 */
	public List<ProductModel> addProduct(ProductModel product);
	
	public void init();
	
	public void destroy();
}
