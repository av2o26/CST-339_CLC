package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


import com.gcu.model.ProductModel;

@Service
public class ProductDataService implements DataAccessInterfaceProduct<ProductModel>{
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public ProductDataService(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public List<ProductModel> findAll()
	{
		String sql = "SELECT * FROM PRODUCT";
		List<ProductModel> orders = new ArrayList<ProductModel>();
		try
		{
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				orders.add(new ProductModel(srs.getString("name"),
						srs.getDouble("price"),
						srs.getString("description"),
						srs.getInt("quantity"),
						srs.getInt("Id")));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return orders;
	}
	
	public boolean create(ProductModel product)
	{
		String sql = "INSERT INTO PRODUCT(name, price, description, quantity) VALUES(?, ?, ?, ?)";
		try
		{
			int rows = jdbcTemplateObject.update(sql,
					product.getName(),
					product.getPrice(),
					product.getDescription(),
					product.getQuantity());
			return rows == 1 ? true : false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(ProductModel product)
	{
		return true;
	}
	
	public boolean delete(ProductModel product)
	{
		return true;
	}

	@Override
	public ProductModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
