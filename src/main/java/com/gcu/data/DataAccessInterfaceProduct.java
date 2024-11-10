package com.gcu.data;

import java.util.List;

public interface DataAccessInterfaceProduct<T> 
{
	public List<T> findAll();
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
}
