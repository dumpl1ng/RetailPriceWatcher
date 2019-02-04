package com.apw.demo.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apw.demo.service.ProductSubService;
import com.apw.demo.service.UserService;
import com.apw.demo.shared.dto.ProductSubDTO;
import com.apw.demo.shared.dto.UserDTO;
import com.apw.demo.ui.model.request.ProductSubRequestModel;
import com.apw.demo.ui.model.request.UserDetailsRequestModel;
import com.apw.demo.ui.model.response.OperationStatus;
import com.apw.demo.ui.model.response.ProductSubResponse;
import com.apw.demo.ui.model.response.RequestOperationStatus;
import com.apw.demo.ui.model.response.UserResponse;


@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductSubService productSubService;
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) //get request send to users/id
	public UserResponse getUser(@PathVariable String id) {
		UserResponse returnValue = new UserResponse();
		
		UserDTO user = userService.getUserById(id);
		BeanUtils.copyProperties(user, returnValue);
		
		return returnValue;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserResponse returnValue = new UserResponse();
		
		if (userDetails.getEmail().isEmpty()) throw new NullPointerException("Email is null");
		
		//UserDto trans = new UserDto(); //not able to map address opject
		//BeanUtils.copyProperties(userDetails, trans);
		
		ModelMapper mapper = new ModelMapper();
		UserDTO trans = mapper.map(userDetails, UserDTO.class);
		
		UserDTO createdUser = userService.createUser(trans);
		returnValue = mapper.map(createdUser, UserResponse.class);
		
		
		return returnValue;
	}
	
	//only when user has signed in, so you have to provide the bearer auth id inside the header
	@PutMapping(path="/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public UserResponse updateUserEmail(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserResponse returnValue = new UserResponse();
		ModelMapper mapper = new ModelMapper();
		
		if (userDetails.getEmail().isEmpty()) throw new NullPointerException("Email is null");
		UserDTO trans = new UserDTO();
		trans = mapper.map(userDetails, UserDTO.class);
		
		UserDTO createdUser = userService.updateUserEmail(id, userDetails.getEmail(), trans);
		returnValue = mapper.map(createdUser, UserResponse.class);
		
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public OperationStatus deleteUser(@PathVariable String id) {
		OperationStatus returnValue = new OperationStatus();
		
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		userService.deleteUser(id);
		
		return returnValue;
	}
	
	
	
	//localhost:8080/price_watcher/users/userId/addresses
	@GetMapping(path="/{id}/addresses", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) //get request send to users/id
	public List<ProductSubResponse> getUserAddresses(@PathVariable String id) {
		ModelMapper mapper = new ModelMapper();
		
		List<ProductSubResponse> returnValue = new ArrayList<>();
		List<ProductSubDTO> productSubDTO = productSubService.getSubs(id);
		
		if (productSubDTO != null && !productSubDTO.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<ProductSubDTO>>() {}.getType();
			returnValue = mapper.map(productSubDTO, listType);
		}
		
		
		
		return returnValue;
	}
	
	
}
