package com.gcu.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcu.data.entity.ProductEntity;

public interface ProductsRepository extends MongoRepository<ProductEntity, String>
{
	ProductEntity getProductById(String id);
}
