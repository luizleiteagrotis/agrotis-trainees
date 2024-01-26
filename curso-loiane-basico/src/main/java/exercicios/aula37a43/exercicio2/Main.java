package exercicios.aula37a43.exercicio2;

import java.util.ArrayList;
import java.util.List;

import exercicios.aula37a43.exercicio2.contribuinte.Contribuinte;
import exercicios.aula37a43.exercicio2.contribuinte.PessoaFisica;
import exercicios.aula37a43.exercicio2.contribuinte.PessoaJuridica;

public class Main {
	
	private static List<Contribuinte> contribuintes = new ArrayList<>();
	
	public static void main(String[] args) {
		adicionarContribuintes();
		mostrarImpostos();
	}
	
	private static void adicionarContribuintes() {
		adicionarPessoasJuridicas();
		adicionarPessoasFisicas();
	}
	
	private static void adicionarPessoasJuridicas() {
		for (int i = 0; i < 3; i++) {
			double rendaBruta = i * 1400;
			String nome = "juridica" + i;
			contribuintes.add(new PessoaJuridica(nome, rendaBruta));
		}
	}
	
	private static void adicionarPessoasFisicas() {
		for (int i = 0; i < 3; i++) {
			double salarioBruto = i * 1800;
			String nome = "fisico" + i;
			contribuintes.add(new PessoaFisica(nome, salarioBruto));
		}
	}
	
	private static void mostrarImpostos() {
		contribuintes.forEach((contribuinte) -> {
			System.out.println(contribuinte);
			System.out.println("Imposto: " + contribuinte.calcularImposto());
			System.out.println();
		});
	}
}
