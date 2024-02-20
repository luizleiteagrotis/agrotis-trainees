package com.agrotis.trainees.crudmenu.informante;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InformanteEntidadeId {

	private final Scanner SCANNER;
	
	@Autowired
	public InformanteEntidadeId(Scanner scanner) {
		SCANNER = scanner;
	}
	
	public long informar() {
		String mensagemErro = "Valor invalido! Digite novamente";
		Long valor = null;
		while (naoInformado(valor)) {
			try {
				System.out.print("Informe um id: ");
				valor = SCANNER.nextLong();
			} catch (InputMismatchException e) {
				System.out.println(mensagemErro);
				SCANNER.nextLine();
			}
		}
		return valor;
	}
	
	private boolean naoInformado(Long valor) {
		return valor == null;
	}
}
