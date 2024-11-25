package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.ProductsDataService;
import com.gcu.data.entity.ProductEntity;
import com.gcu.data.repository.ProductsRepository;
import com.gcu.model.ProductModel;

public class ProductsBusinessService implements ProductsBusinessServiceInterface
{
	@Autowired
	public ProductsDataService service;
	
	@Autowired
	public ProductsRepository productRepo;
	
	@Override
	public List<ProductModel> getProducts() 
	{
		var productList = new ArrayList<ProductModel>();
		
		var productsEntity = service.findAll();
		
		for (ProductEntity entity : productsEntity)
		{
			productList.add(new ProductModel(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getQuantity()));
		}
		
		// Return list of products
		return productList;
	}

	@Override
	public ProductModel getProductById(String id)
	{
		var productEntity = service.findByID(id);
		if (productEntity != null)
			return new ProductModel(productEntity.getId(), productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getQuantity());
		else
			return null;
	}
	
	@Override
	public List<ProductModel> addProduct(ProductModel product) 
	{		
		ProductEntity productEntity = new ProductEntity(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getQuantity());
		
		service.create(productEntity);
		
		// Return list of products
		return getProducts();
	}
	
	@Override
	public List<ProductModel> updateProduct(ProductModel product)
	{
		ProductEntity productEntity = new ProductEntity(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getQuantity());
		
		service.update(productEntity);
		
		// Return list of products
		return getProducts();
	}
	
	@Override
	public void deleteProductById(String productId) {
	    // Assuming you have a repository for ProductEntity
		productRepo.deleteById(productId);
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
