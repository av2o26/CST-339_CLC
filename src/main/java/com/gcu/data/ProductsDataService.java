package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.ProductEntity;
import com.gcu.data.repository.ProductsRepository;


@Service
public class ProductsDataService implements ProductsDataAccessInterface<ProductEntity>
{	
	@Autowired
	private ProductsRepository productsRepository;
	
	public ProductsDataService(ProductsRepository productsRepository)
	{
		this.productsRepository = productsRepository;
	}
	
	public List<ProductEntity> findAll()
	{
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		try
		{
			// Set all the Entity Orders
			Iterable<ProductEntity> productsIterable = productsRepository.findAll();
			
			// Convert and return the list
			products = new ArrayList<ProductEntity>();
			productsIterable.forEach(products::add);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return products;
	}
	
	public ProductEntity findByID(String id)
	{
		return productsRepository.getProductById(id);
	}
	
	public boolean create(ProductEntity product)
	{
		try
		{
			this.productsRepository.save(product);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean update(ProductEntity product)
	{
		// TODO: Rework for Mongo
		try
		{
			productsRepository.save(product);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean delete(ProductEntity product)
	{
		// TODO: Rework for Mongo
		try
		{
			productsRepository.delete(product);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
