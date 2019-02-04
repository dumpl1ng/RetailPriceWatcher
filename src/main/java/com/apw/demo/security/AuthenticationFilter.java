package com.apw.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apw.demo.SpringApplicationContext;
import com.apw.demo.service.UserService;
import com.apw.demo.shared.dto.UserDTO;
import com.apw.demo.ui.model.request.UserLogInRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override //when user try to login with email and password, this method is called
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	
        	
            UserLogInRequestModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserLogInRequestModel.class);
            //will try to map the authentication request into the user log in request object
            
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	@Override //user will store the token into the keychain, and when user communicate with our api, it needs to include the header
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        
        String userName = ((User) auth.getPrincipal()).getUsername();  
        
        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
                .compact();
        UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl"); 
        //spring will load bean name as class name except the first letter will be lower case
        UserDTO userDto = userService.getUserByEmail(userName);
        
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        res.addHeader("UserID", userDto.getUserId());

    } 
	
	
}
