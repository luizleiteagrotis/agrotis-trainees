package com.agrotis.trainees.crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClassConfiguration {

	@Bean
	public <T> Class<T> classType() {
		return (Class<T>) getClass();
	}
}
