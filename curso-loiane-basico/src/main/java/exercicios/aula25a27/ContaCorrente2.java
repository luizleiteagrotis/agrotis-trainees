package exercicios.aula25a27;

import java.util.Scanner;

public class ContaCorrente2 {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		ContaCorrente contacorrente =  new ContaCorrente();
		
		contacorrente.nomeCompleto = "Vitor de Campos";
		contacorrente.agencia = 3720;
		contacorrente.conta = 52336;
		contacorrente.digito = 1;
		contacorrente.especial = "Sim";
		contacorrente.saldo = 127.00;
		
		contacorrente.menu();
		
		int opcao = sc.nextInt();
		
		if (opcao == 1){
			contacorrente.consultarSaldo();
		} else if (opcao == 2) {
			contacorrente.sacarDinheiro();
		} else if (opcao == 3) {
			contacorrente.depositarDinheiro();
		} else if (opcao == 4) {
			contacorrente.verificarChequeEspecial();
		}
		
		System.out.println();
		System.out.println("Digite '0' - Para voltar ao menu");
		opcao = sc.nextInt();
		if (opcao == 0) {
			contacorrente.menu();
		}
		
	}
}