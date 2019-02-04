package com.apw.demo.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apw.demo.exceptions.ErrorMessages;
import com.apw.demo.exceptions.UserServiceException;
import com.apw.demo.io.entity.ProductSubEntity;
import com.apw.demo.io.entity.UserEntity;
import com.apw.demo.io.repository.ProductSubRepository;
import com.apw.demo.io.repository.UserRepository;
import com.apw.demo.service.UserService;
import com.apw.demo.share.Util;
import com.apw.demo.shared.dto.ProductSubDTO;
import com.apw.demo.shared.dto.UserDTO;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductSubRepository subRepository;
	
	@Autowired
	Util util;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByEmail(username);
		
		if (entity == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		
		return new User(entity.getEmail(), entity.getEncryptedPassword(), new ArrayList<>());
		
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		UserDTO returnValue = new UserDTO();
		
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("user already exists!");
		}
		
		ModelMapper mapper = new ModelMapper();
		
		UserEntity userEntity = new UserEntity();
		userEntity = mapper.map(user, UserEntity.class);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(util.generateID(50));
		
		UserEntity savedEntity = userRepository.save(userEntity);
		returnValue = mapper.map(savedEntity, UserDTO.class);
		
		return returnValue;
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		UserEntity entity = userRepository.findByEmail(email);
		
		if (entity == null) {
			throw new UsernameNotFoundException(email);
		}
		
		ModelMapper mapper = new ModelMapper();
		UserDTO returnValue = mapper.map(entity, UserDTO.class);
		
		for loop iterate thru the sub lists
		
		return returnValue;
	}

	@Override
	public UserDTO getUserById(String userId) {
		UserEntity entity = userRepository.findByUserId(userId);
		
		if (entity == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		ModelMapper mapper = new ModelMapper();
		UserDTO returnValue = mapper.map(entity, UserDTO.class);
		
		return returnValue;
	}

	@Override
	public UserDTO updateUserEmail(String userId, String email, UserDTO user) {
		UserEntity entity = userRepository.findByUserId(userId);
		
		if (entity == null) {
			throw new UsernameNotFoundException("Update User: "+ userId);
		}
		
		entity.setEmail(email);
		UserEntity updateEntity = userRepository.save(entity);
		
		ModelMapper mapper = new ModelMapper();
		UserDTO returnValue = mapper.map(updateEntity, UserDTO.class);
		
		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userToDelete = userRepository.findByUserId(userId);
		
		if (userToDelete == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userToDelete);
		
	}

	
	
	

	

	
	
}
