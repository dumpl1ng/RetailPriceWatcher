package com.apw.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.apw.demo.shared.dto.ProductSubDTO;
import com.apw.demo.shared.dto.UserDTO;

public interface UserService extends UserDetailsService{

	UserDTO createUser(UserDTO user);
		
	UserDTO getUserByEmail(String email);
	
	UserDTO getUserById(String userId);
	
	void deleteUser(String userId);

	UserDTO updateUserEmail(String userId, String email, UserDTO user);
}
