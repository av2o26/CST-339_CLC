package com.gcu.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcu.data.entity.UsersEntity;

public interface UsersRepository extends CrudRepository<UsersEntity, Long>
{
	
}
