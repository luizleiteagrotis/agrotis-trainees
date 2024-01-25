package CondicionaisIfElse;

import java.util.Scanner;

public class Exemplo3 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com um dia da semana (1-7):");
		int diaSemana = scan.nextInt();
		
		/*if (diaSemana == 1) {
			System.out.println("Domingo");
		} else if (diaSemana == 2){
			System.out.println("Segunda");
		} else if (diaSemana == 3){
			System.out.println("Terça");
		} else if (diaSemana == 4){
			System.out.println("Quarta");
		} else if (diaSemana == 5){
			System.out.println("Quinta");
		} else if (diaSemana == 6){
			System.out.println("Sexta");
		} else if (diaSemana == 7){
			System.out.println("Sábado");
		} else {
			System.out.println("Não é um dia da semana válido!");
		}*/
		
		/*switch(diaSemana) {
		case 1: System.out.println("Domingo");
		case 2: System.out.println("Segunda");
		case 3: System.out.println("Terça"); 
		case 4: System.out.println("Quarta");
		case 5: System.out.println("Quinta");
		case 6: System.out.println("Sexta"); 
		case 7: System.out.println("Sábado"); break;
		default: System.out.println("Não é um dia da semana válido!");
		}*/
		switch(diaSemana) {
		case 2:
		case 3: 
		case 4:
		case 5:
		case 6: System.out.println("Dia útil"); break; 
		case 1: 
		case 7: System.out.println("Fim de semana"); break;
		default: System.out.println("Não é um dia da semana válido!");
		}
	}
}