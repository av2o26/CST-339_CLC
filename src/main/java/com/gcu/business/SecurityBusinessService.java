package com.gcu.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.model.UserModel;

@Service
public class SecurityBusinessService 
{
	/**
	 * Check that registration data is valid
	 * @param userModels
	 * @param user
	 * @return true if username is taken
	 */
	public boolean doesUsernameExist(List<UserModel> userModels, UserModel user)
	{
		for (UserModel existingUser : userModels) 
		{
			if(existingUser.getUsername().equals(user.getUsername()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Check for username and password combination
	 * @param userModels
	 * @param user
	 * @return true if login info is valid
	 */
	public boolean verifyLogin(List<UserModel> userModels, UserModel user)
	{
		for (int i = 0; i < userModels.size(); i++)
		{
			if(userModels.get(i).getUsername().equals(user.getUsername()))
			{
				
				if (userModels.get(i).getPassword().equals(user.getPassword()))
				{
					// If username and password matched an account, log into shop view
					return true;
				}
			}
		}
		
		// Return false if no match was found
		return false;
	}
}
