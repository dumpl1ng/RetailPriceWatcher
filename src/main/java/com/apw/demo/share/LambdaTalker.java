package com.apw.demo.share;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.apw.demo.exceptions.ProductSubException;
import com.apw.demo.exceptions.UserServiceException;
import com.apw.demo.shared.dto.ProductSubDTO;

public class LambdaTalker {
	
	private String charset = "UTF-8";
	
	
	public LambdaTalker() {
		
	}
	
	public ProductSubDTO talk(String productUrl) {
		
		ProductSubDTO returnValue = new ProductSubDTO();
		String productId = "";
		String query = "";
		String lambdaUrl = "https://7e2d6wswhg.execute-api.us-east-1.amazonaws.com/default/PriceWatcher";
		String pattern = "prod(\\d+)";
		
		
		
		
		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(productUrl);
			if(m.find()){
		        productId = m.group(1);
		    }
			
			
			query = String.format("productId=%s", 
				     URLEncoder.encode(productId, charset));
			
			URLConnection connection = new URL(lambdaUrl + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();
			
			
			try (Scanner scanner = new Scanner(response)) {
			    String responseBody = scanner.useDelimiter("\\A").next();
			    if(responseBody.contains("error")) {
			    	throw new ProductSubException("product not found");
			    }
			    String productName = responseBody.split("\"")[3];
			    int productPrice = parseStringToInt(responseBody.split("\"")[7]);
			    
			    returnValue.setCurrentPrice(productPrice);
			    returnValue.setSubName(productName);
			    returnValue.setSubId(productId);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			talk(productUrl);
		} /*catch (IllegalStateException e) {
			throw new UserServiceException("invalid url");
		}*/
		
		
		return returnValue;
		
	}
	
	public static int parseStringToInt(String value) {
		String temp = "";
		char dot = '.';
		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) == dot) {
				break;
			}
			if (Character.isDigit(value.charAt(i))) {
				temp += value.charAt(i);
			}
			
		}
		return Integer.parseInt(temp);
	}
	
	
}
