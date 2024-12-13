package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.data.UsersDataService;
import com.gcu.data.entity.UserEntity;

@Service
public class UsersBusinessService implements UserDetailsService
{
	@Autowired
	UsersDataService service;

	public UsersBusinessService(UsersDataService service)
	{
		this.service = service;
	}
	
	public boolean createUser(UserEntity user)
	{
		if (service.createUser(user))
			return true;
		else
			return false;
	}
	
	/**
	 * Used to support String security user authentication
	 * @param username
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		// Try to find the user in the database. If not found, throw a UsernameNotFoundException.
		// Else, return the Spring Security user
		UserEntity user = service.findUserByUsername(username);
		
		if (user != null)
		{
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			return new User(user.getUsername(), user.getPassword(), authorities);
		}
		else
		{
			throw new UsernameNotFoundException("Username not found");
		}
	}
}
