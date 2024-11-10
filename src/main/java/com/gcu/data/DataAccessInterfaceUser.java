package com.gcu.data;

public interface DataAccessInterfaceUser<T> 
{
	public T findUserByUsername(String username);
	public boolean createUser(T t);
}
