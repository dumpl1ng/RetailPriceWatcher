package com.apw.demo.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apw.demo.io.entity.UserEntity;



@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {//use for database operation
	
	
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId); //spring query JPA will find for us
	//keywords: 'find' 'By' + column name
	
	

}
