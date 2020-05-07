package com.api.rest;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"com.api.rest.model"})
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.api.rest.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
@EnableCaching


public class ApiApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static void main(String[] args) {
		
		/*final String ACCOUNT_SID = "ACd8d726deb2d9d3f9c322a784f20dbb09";
	    final String AUTH_TOKEN ="cfe79349c4733728f84f29d0a16d8279";

	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	        Message message = Message
	                .creator(new PhoneNumber("+3530833812801"), // to
	                        new PhoneNumber("+12512903313"), // from
	                        "Sms working!")
	                .create();

	        System.out.println(message.getSid());*/
	
	        
		SpringApplication.run(ApiApplication.class, args);
	}
	
}