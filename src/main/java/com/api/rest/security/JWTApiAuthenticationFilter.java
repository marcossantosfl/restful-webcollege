package com.api.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTApiAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
	      HttpServletResponse response = (HttpServletResponse) res;
	      HttpServletRequest request = (HttpServletRequest) req;
	      
	
		  response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
	      response.setHeader("Access-Control-Max-Age", "3600");
	      response.setHeader("Access-Control-Allow-Credentials", "true");

	      if(!(request.getMethod().equalsIgnoreCase("OPTIONS"))) 
	      {
	          try 
	          {
	      		Authentication authentication = new JWTTokenAuthenticationService().getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);

	    		SecurityContextHolder.getContext().setAuthentication(authentication);

	    		chain.doFilter(req, res);
	          } 
	          catch(Exception e) 
	          {
	          }
	      } else 
	      {
	          response.setHeader("Access-Control-Allow-Origin", "*");
	          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
	          response.setHeader("Access-Control-Max-Age", "3600");
	          response.setHeader("Access-Control-Allow-Headers", "*");
	          response.setStatus(HttpServletResponse.SC_OK);
	      }

	}

}
