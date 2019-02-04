package com.apw.demo.share;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component //for dependency injection
public class Util {

	
	private final Random RANDOM = new SecureRandom();
	private final String choice = "0123456789abcdefghijkomnopqrstuvwxyz";
	
	public String generateID(int length) {
		return generateUserID(length);
	}
	
	
	private String generateUserID(int length) {
		StringBuilder result = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			result.append(choice.charAt(RANDOM.nextInt(choice.length())));
		}
		
		return new String(result);
		
	}
}
