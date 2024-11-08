package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.gcu.model.UserModel;

@Service
public class UserDataService implements DataAccessInterfaceUser<UserModel> {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	
	
	public UserDataService(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public List<UserModel> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public UserModel findUserByUsername(String username) {
		String sql = "SELECT * FROM USERTABLE WHERE userName = ?";
	    try {
	        return jdbcTemplateObject.query(sql, new Object[]{username}, (rs, rowNum) -> {
	            UserModel user = new UserModel();
	            user.setFirstName(rs.getString("firstName"));
	            user.setLastName(rs.getString("lastName"));
	            user.setEmail(rs.getString("email"));
	            user.setPhone(rs.getString("phoneNumber"));
	            user.setUsername(rs.getString("userName"));
	            user.setPassword(rs.getString("password")); // Be cautious with storing passwords
	            return user;
	        }).stream().findFirst().orElse(null); // Get the first result or return null
	    } catch (EmptyResultDataAccessException e) {
	        return null; // User not found
	    }
	}

	@Override
	public boolean createUser(UserModel user) {
		String sql = "INSERT INTO USERTABLE(firstName, lastName, email, phoneNumber, userName, password) VALUES(?, ?, ?, ?, ?, ?)"; // Adjust as necessary
        try {
            int rows = jdbcTemplateObject.update(sql,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getUsername(),
                    user.getPassword());
            return rows == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	

	@Override
	public boolean updateUser(UserModel user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(UserModel user) {
		// TODO Auto-generated method stub
		return false;
	}

}
