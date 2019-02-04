package com.apw.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;


//when a user want to update his details, he has to provide user id and assigned token to be authorized
public class AuthorizationFilter extends BasicAuthenticationFilter {
    
    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
     }
    
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        
        
        //if header is null or doesnot start with 'bearer', continue to next request
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }  
    
    
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        
        if (token != null) {
            
            token = token.replace(SecurityConstants.TOKEN_PREFIX, ""); 
            //we don't need the "bearer", just the token string
            
            String user = Jwts.parser()
                    .setSigningKey( SecurityConstants.TOKEN_SECRET )
                    .parseClaimsJws( token )
                    .getBody()
                    .getSubject(); //let the frame work parse the user details from the token
            		//need to provide the token_secret and token
            
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            
            return null;
        }
        
        return null;
    }
    
}