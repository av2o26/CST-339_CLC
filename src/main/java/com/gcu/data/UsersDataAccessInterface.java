package com.gcu.data;

public interface UsersDataAccessInterface<T> 
{
	public T findUserByUsername(String username);
	public boolean createUser(T t);
}
