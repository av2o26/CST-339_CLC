package com.gcu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.ProductsBusinessServiceInterface;
import com.gcu.model.ProductModel;

@RestController
@RequestMapping("service")
public class ProductsRestController {
	
	@Autowired
	private ProductsBusinessServiceInterface service;
	
	@GetMapping(path="/allProducts")
	public List<ProductModel> getAllProducts()
	{
		return service.getProducts();
	}
	
	@GetMapping(path="/getProduct/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") String id)
	{
		try {
			ProductModel product = service.getProductById(id);
			if(product == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<>(product, HttpStatus.OK);
			}
		}catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
