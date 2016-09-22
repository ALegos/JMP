package com.epam.jmp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.jmp.persistance.PersistanceUnit;

@Configuration
public class PersistanceConfiguration {
	
	@Bean
	PersistanceUnit persistaceUnitInit(){
		return new PersistanceUnit();
	}
	
}
