package com.gcu.data;

import java.util.List;

public interface ProductsDataAccessInterface<T> 
{
	public List<T> findAll();
	public T findByID(String id);
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
}
