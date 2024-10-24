package com.example.ToDo.controller.config;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);

	@Bean
	public ModelMapper getModelMapper() {
		logger.info("Creating ModelMapper bean");
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncode() {
		logger.info("Creating PasswordEncoder bean");
		return new BCryptPasswordEncoder();
	}
}