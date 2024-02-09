package com.agrotis.trainees.crud.menu.informante;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InformanteData {
	
	private final Scanner SCANNER;
	
	@Autowired
	public InformanteData(Scanner scanner) {
		SCANNER = scanner;
	}
	
	public LocalDate informar() {
		LocalDate data = null;
		while (naoInformado(data)) {
			System.out.print("Informe o dia: ");
			int dia = SCANNER.nextInt();
			System.out.print("Informe o mes: ");
			int mes = SCANNER.nextInt();
			System.out.print("Informe o ano: ");
			int ano = SCANNER.nextInt();
			try {
				data = LocalDate.of(ano, mes, dia);
			} catch(DateTimeException e) {
				System.out.println("Data invalida!");
			} 
		}
		return data;
	}
	
	private boolean naoInformado(LocalDate data) {
		return data == null;
	}
}
