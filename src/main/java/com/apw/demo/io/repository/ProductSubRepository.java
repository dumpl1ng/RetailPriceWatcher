package com.apw.demo.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apw.demo.io.entity.ProductSubEntity;
import com.apw.demo.io.entity.UserEntity;

@Repository
public interface ProductSubRepository extends CrudRepository<ProductSubEntity, Long>{
	

	List<ProductSubEntity> findAllByUserDetails(UserEntity user);
	
	ProductSubEntity findBySubId(String subId);
}
