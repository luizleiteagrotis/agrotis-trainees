package exercicios.aula25a27;

import java.util.Scanner;

public class ContaCorrente {
	
	Scanner sc = new Scanner(System.in);
	
	String nomeCompleto;
	int agencia;
	int conta;
	int digito;
	double saldo;
	double limite;
	String status;
	String especial;
	double valorADepositar;
	double mostraSaldoAtual;
	String menu;
	
	void consultarSaldo() {
		System.out.println("Seu saldo atual é: " + saldo);
	}
	
	void menu() {
		System.out.println("Escolha uma das opções abaixo: ");
		System.out.println("Digite '1' - Para consultar seu saldo;");
		System.out.println("Digite '2' - Para SAQUE;");
		System.out.println("Digite '3' - Para DEPÓSITO;");
		System.out.println("Digite '4' - Para verificar cheque especial.");
	}
	
	double sacarDinheiro() {
		if (saldo > 0){
			System.out.println("Você pode sacar R$" + saldo);
		} else if (saldo <= 0){
			System.out.println("Você não possui saldo");
		}
	return saldo;
	}
	
	double depositarDinheiro() {
		System.out.println("Entre com o valor que deseja depositar na sua conta: ");
		valorADepositar = sc.nextDouble();
		mostraSaldoAtual = saldo + valorADepositar;
		System.out.println("Seu saldo atual é ");
		return mostraSaldoAtual;
	}
	
	double verificarChequeEspecial() {
		if (saldo < 0){
		System.out.println("Você está utilizando o cheque especial");
		} else if (saldo >= 0) {
			System.out.println("Você não está utilizando o cheque especial. Seu saldo é: " + saldo);
		}
		return saldo;
	}
	
}