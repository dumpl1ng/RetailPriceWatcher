package com.apw.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apw.demo.shared.dto.ProductSubDTO;


public interface ProductSubService{
	
	ProductSubDTO createSub(String userId, String url);

	List<ProductSubDTO> getSubs(String userId);
	
	List<ProductSubDTO> deleteSubs(String subId);
}
