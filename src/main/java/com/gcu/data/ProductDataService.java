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
public class ProductDataService implements DataAccessInterfaceProduct<ProductModel>
{	
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(ProductModel product)
	{
		String sql = "UPDATE PRODUCT SET name = ?, price = ?, description = ?, quantity = ? WHERE Id = ?";
		try
		{
			int rows = jdbcTemplateObject.update(sql, 
					product.getName(), 
					product.getPrice(), 
					product.getDescription(),
					product.getQuantity(),
					product.getId());
			return rows == 1 ? true : false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean delete(ProductModel product)
	{
		String sql = "DELETE FROM product WHERE id = ?";
		try
		{
			int rows = jdbcTemplateObject.update(sql, product.getId()); 
			return rows == 1 ? true : false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
}
