package com.agrotis.trainees.crudmenu.configuration;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScannerConfiguration {
	
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
