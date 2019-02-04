package com.apw.demo.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.apw.demo.service.UserService;


@EnableWebSecurity //default login url is localhost:8080/login
public class WebSecurity extends WebSecurityConfigurerAdapter{

    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
    	http.csrf().disable().authorizeRequests().
    	antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL). //let the framework permit all sign up request and authenticate
    	permitAll().anyRequest().authenticated().and().addFilter(getAuthenticationFilter())
    	.addFilter(new AuthorizationFilter(authenticationManager()))
    	.sessionManagement()
    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
    }
    
    @Override //encryption of the password
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    //reset sign in url
    public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
}
