package exercicios.aula37a43.exercicio1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static ArrayList<ContaBancaria> contas = new ArrayList<>();
	
	public static void main(String[] args) {
		criarContasBancarias();
		System.out.println("DEPOSITAR");
		depositarValor();
		System.out.println();
		System.out.println();
		System.out.println("SACAR");
		sacarValor();
		System.out.println();
		System.out.println();
		System.out.println("RENDIMENTOS CONTA POUPANCA");
		passarDia();
	}
	
	private static void criarContasBancarias() {
		criarContasPoupanca();
		criarContasEspeciais();
	}
	
	private static void criarContasPoupanca() {
		for (int i = 1; i < 3; i++) {
			contas.add(new ContaPoupanca("cliente" + i));
		}
	}
	
	private static void criarContasEspeciais() {
		for (int i = 3; i < 5; i++) {
			contas.add(new ContaEspecial("cliente" + i));
		}
	}
	
	private static void depositarValor() {
		final Scanner SCANNER = new Scanner(System.in);
		contas.forEach((conta) -> {
			System.out.println(conta);
			System.out.print("Digite um valor para depositar: ");
			double valor = SCANNER.nextDouble();
			conta.depositar(valor);
			System.out.println(conta);
			System.out.println();
		});
	}
	
	private static void sacarValor() {
		final Scanner SCANNER = new Scanner(System.in);
		contas.forEach((conta) -> {
			System.out.println(conta);
			System.out.print("Digite um valor para sacar: ");
			double valor = SCANNER.nextDouble();
			try {
				conta.sacar(valor);
			} catch (ContaBancariaException e) {
				System.out.println(e.getMessage());
			}
			System.out.println(conta);
			System.out.println();
		});
	}
	
	private static void passarDia() {
		contas.forEach((conta) -> {
			if (conta instanceof ContaPoupanca) {
				System.out.println(conta);
				((ContaPoupanca) conta).calcularNovoSaldo();
				System.out.println(conta);
				System.out.println();
			}
		});
	}
}
