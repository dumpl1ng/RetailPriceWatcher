package com.apw.demo.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apw.demo.exceptions.ProductSubException;
import com.apw.demo.io.entity.ProductSubEntity;
import com.apw.demo.io.entity.UserEntity;
import com.apw.demo.io.repository.ProductSubRepository;
import com.apw.demo.io.repository.UserRepository;
import com.apw.demo.service.ProductSubService;
import com.apw.demo.share.LambdaTalker;
import com.apw.demo.shared.dto.ProductSubDTO;

@Service
public class ProductSubServiceImpl implements ProductSubService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductSubRepository productSubRepository;
	
	@Override
	public List<ProductSubDTO> getSubs(String userId) {
		List<ProductSubDTO> returnValue = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();
		UserEntity entity = userRepository.findByUserId(userId);
		
		if (entity == null) {
			throw new UsernameNotFoundException(userId);
		}
		Iterable<ProductSubEntity> subs = productSubRepository.findAllByUserDetails(entity);
		for (ProductSubEntity p: subs) {
			returnValue.add(mapper.map(p, ProductSubDTO.class));
		}
		
		return returnValue;
	}

	@Override
	public List<ProductSubDTO> deleteSubs(String subId) {
		
		ProductSubEntity subToDelete = productSubRepository.findBySubId(subId);
		
		if (subToDelete == null) throw new ProductSubException("Sub not found!");
		
		String userId = subToDelete.getUserDetails().getUserId();
		productSubRepository.delete(subToDelete);
		
		
		
		return getSubs(userId);
	}

	@Override
	public ProductSubDTO createSub(String userId, String url) {
		LambdaTalker lambda = new LambdaTalker();
		ProductSubDTO returnValue = new ProductSubDTO();
		UserEntity userEntity = userRepository.findByUserId(userId);
		ModelMapper mapper = new ModelMapper();
		
		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		ProductSubDTO newSub = lambda.talk(url);
		newSub.setDiscount(0);
		newSub.setOldPrice(newSub.getCurrentPrice());
		newSub.setSubUrl(url);
		
		ProductSubEntity subEntity = mapper.map(newSub, ProductSubEntity.class);
		subEntity.setUserDetails(userEntity);
		userEntity.getSubs().add(subEntity);
		
		
		
		userRepository.save(userEntity);
		ProductSubEntity savedEntity = productSubRepository.save(subEntity);
		
		returnValue = mapper.map(savedEntity, ProductSubDTO.class);
		return returnValue;
	}

	

}
