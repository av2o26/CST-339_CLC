package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.ProductsBusinessService;
import com.gcu.business.ProductsBusinessServiceInterface;


@Configuration
public class SpringConfig 
{
    @Bean(name = "ProductsBusinessService", initMethod = "init", destroyMethod = "destroy")
    ProductsBusinessServiceInterface getproductsBusiness()
	{
		return new ProductsBusinessService();
	}	
}
