package com.apptmyz.fpgreetings.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
@Configurable
public class JwtUtil {
	
	private String key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
	
	public String createJWTToken(String data, int min) throws UnsupportedEncodingException{
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, min);
		Date expirationDate = c.getTime();
		
		String jwt = Jwts.builder()
				  .setSubject("FIngPay/AuthServices")
				  .setExpiration(expirationDate)
				  .claim("name", "TokenData")
				  .claim("scope", "Merchant/payments")
				  .claim("data", data)
				  .signWith(
				    SignatureAlgorithm.HS256,
				    key.getBytes("UTF-8")
				  ).compact();
//		System.out.println(jwt);
		return jwt;
	}
	
	public String parseJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException{
		Jws<Claims> claims = Jwts.parser()
				  .setSigningKey(key.getBytes("UTF-8"))
				  .parseClaimsJws(jwt);
				String name = (String) claims.getBody().get("name");
				String scope = (String) claims.getBody().get("scope");
				String data = (String) claims.getBody().get("data");
				
//				System.out.println(name);	
//				System.out.println(scope);
//				System.out.println(data);
				return data;
	}
	
	  public static void main(String[] args) throws UnsupportedEncodingException {
//		  String data = "{\"status\":true,\"message\":\"Aadhar Number already mapped with this phonenumber\",\"data\":10007,\"statusCode\":0}";
//		  System.out.println(createJWTToken(data, 10000));
//		  parseJWT(createJWTToken(data, 1));
		  JwtUtil j=new JwtUtil();
		  String data="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJGSW5nUGF5L0F1dGhTZXJ2aWNlcyIsImV4cCI6MTYzMDMzMjYxMCwibmFtZSI6IlRva2VuRGF0YSIsInNjb3BlIjoiTWVyY2hhbnQvcGF5bWVudHMiLCJkYXRhIjoie1widXNlcklkXCI6NDgsXCJ1c2VybmFtZVwiOlwidnlzaG5hdmlcIixcInVzZXJUeXBlXCI6MTAxLFwiYWN0aXZlRmxhZ1wiOjEsXCJhZG1pblJhZGlvQnV0dG9uXCI6MSxcImZvcmNlUHdkQ2hhbmdlXCI6ZmFsc2V9In0.kPzUC8LZrySmOl1eLKflUCNxGwLAjpSbnH6BemAbyzo";
		  System.out.println(j.parseJWT(data));
	  }

}
