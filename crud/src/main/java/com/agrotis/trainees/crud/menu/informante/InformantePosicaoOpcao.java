package com.agrotis.trainees.crud.menu.informante;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.menu.opcao.OpcaoMenu;

@Component
public class InformantePosicaoOpcao {
	
	private int minInclusivo;
	private int maxInclusivo;
	private final Scanner SCANNER;
	
	@Autowired
	public InformantePosicaoOpcao(Scanner scanner) {
		SCANNER = scanner;
	}

	public int informar(int minInclusivo, int maxInclusivo) {
		this.minInclusivo = minInclusivo;
		this.maxInclusivo = maxInclusivo;
		Integer posicaoOpcao = null;
		while(naoExiste(posicaoOpcao)) {
			System.out.print("Escolha uma opcao: ");
			try {
				posicaoOpcao = SCANNER.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Valor digitado invalido!");
				SCANNER.nextLine();
				continue;
			}
			if (naoExiste(posicaoOpcao)) System.out.println("Opcao " + posicaoOpcao + " nao existe");
		}
		return posicaoOpcao;
	}
	
	private boolean naoExiste(Integer opcao) {
		return (opcao == null) || (opcao < minInclusivo) || (opcao > maxInclusivo);
	}
}
