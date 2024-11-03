package com.gcu.data;

import java.util.List;

public interface DataAccessInterfaceUser<T> {
	public List<T> findAllUsers();
	public T findUserByUsername(String username);
	public boolean createUser(T t);
	public boolean updateUser(T t);
	public boolean deleteUser(T t);
}
