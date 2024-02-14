package com.agrotis.trainees.crudmenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crudmenu.menu.CrudMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CrudMenuApplication implements CommandLineRunner {
	
	private CrudMenu menu;
	
	@Autowired
	public CrudMenuApplication(CrudMenu menu) {
		this.menu = menu;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CrudMenuApplication.class, args);
	}

	@Override
	public void run(String... args) {
		menu.iniciar();
	}	
}
