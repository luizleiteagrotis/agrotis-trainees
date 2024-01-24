package exercicios.aula24;

import java.util.Scanner;

public class ContaCorrente2 {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		ContaCorrente contacorrente =  new ContaCorrente();
		
		contacorrente.nomeCompleto = sc.nextLine();
		contacorrente.agencia = sc.nextInt();
				
		
	}
}