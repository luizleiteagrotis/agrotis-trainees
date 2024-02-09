package exercicios.aula25a27;

import java.util.Scanner;

public class ContaCorrenteTeste {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ContaCorrente conta = new ContaCorrente();
		conta.codigo = 123456;
		conta.especial = false;
		conta.limite = 20000;
		conta.saldo = 500;
		
		System.out.println("Seu saldo é: R$ " + String.format("%.2f", conta.saldo) );
		System.out.println(conta.especial ? "Você está usando o cheque especial" : "Você não está usando cheque especial");
		
		System.out.println("Deseja realizar um depósito ou saque?");
		
		String operacao = scan.next().toLowerCase();
		
		if(operacao.equals("deposito")) {
			System.out.println("Quanto deseja depositar?");
			double deposito = scan.nextDouble();
			conta.depositarValor(deposito);
			System.out.println("Operação realizada com sucesso!");
			System.out.println("Seu novo saldo é: R$ " + String.format("%.2f", conta.saldo) );
		} else if( operacao.equals("saque")) {
			System.out.println("Quanto deseja sacar?");
			double saque = scan.nextDouble();
			
			if(conta.sacarValor(saque) != 0) {
				System.out.println("Operação realizada com sucesso!");
				System.out.println("Seu novo saldo é: R$ " + String.format("%.2f", conta.saldo) );
			}
		}
		
		
		
	}

}
