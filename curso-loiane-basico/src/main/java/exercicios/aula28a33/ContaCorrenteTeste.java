package exercicios.aula28a33;

import java.util.Scanner;

public class ContaCorrenteTeste {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ContaCorrente conta = new ContaCorrente(123, 500, 2000, true);
		
		
		System.out.println("Seu saldo é: R$ " + String.format("%.2f", conta.getSaldo()) );
		System.out.println(conta.isEspecial() ? "Você está usando o cheque especial" : "Você não está usando cheque especial");
		
		System.out.println("Deseja realizar um depósito ou saque?");
		
		String operacao = scan.next().toLowerCase();
		
		if(operacao.equals("deposito")) {
			System.out.println("Quanto deseja depositar?");
			double deposito = scan.nextDouble();
			conta.depositarValor(deposito);
			System.out.println("Operação realizada com sucesso!");
			System.out.println("Seu novo saldo é: R$ " + String.format("%.2f", conta.getSaldo()) );
		} else if( operacao.equals("saque")) {
			System.out.println("Quanto deseja sacar?");
			double saque = scan.nextDouble();
			
			if(conta.sacarValor(saque) != 0) {
				System.out.println("Operação realizada com sucesso!");
				System.out.println("Seu novo saldo é: R$ " + String.format("%.2f", conta.getSaldo()) );
			}
		}
		
		
		
	}
}
