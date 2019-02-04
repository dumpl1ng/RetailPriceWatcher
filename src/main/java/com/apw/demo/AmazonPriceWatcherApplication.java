package com.apw.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication
public class AmazonPriceWatcherApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AmazonPriceWatcherApplication.class, args);
	}
	
	@Override //for deploying into apache
	protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
		return app.sources(AmazonPriceWatcherApplication.class);
	}
	
	
	@Bean //By annotated "bean" we can autowire it
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

