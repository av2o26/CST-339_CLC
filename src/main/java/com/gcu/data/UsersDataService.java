package com.gcu.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UsersRepository;

@Service
public class UsersDataService implements UsersDataAccessInterface<UserEntity>
{
	@Autowired
	UsersRepository usersRepository;

	public UsersDataService(UsersRepository usersRepository)
	{
		this.usersRepository = usersRepository;
	}

	/**
	 * Get a user by matching with the requested username
	 */
	@Override
	public UserEntity findUserByUsername(String username)
	{
		return usersRepository.findByUsername(username);
	}

	/**
	 * Create a user
	 */
	@Override
	public boolean createUser(UserEntity user)
	{
		try
		{
			// Encode password to database
			String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encoded);
			
			this.usersRepository.save(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
