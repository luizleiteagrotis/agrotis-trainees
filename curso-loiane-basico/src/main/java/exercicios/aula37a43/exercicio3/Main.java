package exercicios.aula37a43.exercicio3;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static List<Animal> animais = new ArrayList<>();
	
	public static void main(String[] args) {
		adicionarAnimais();
		mostrarAnimais();
	}

	private static void mostrarAnimais() {
		System.out.println("Zoo:");
		animais.forEach((animal) -> {
			System.out.println("-------------------------");
			System.out.println(animal);
		});
		System.out.println("-------------------------");
	}

	private static void adicionarAnimais() {
		animais.add(new Mamifero(nome("Camelo"), comprimento(150), velocidade(2), Cor.AMARELO));
		animais.add(new Peixe(nome("Tubarao"), comprimento(300), velocidade(1.5)));
		animais.add(new Urso(nome("Urso-do-canada"), comprimento(180), velocidade(0.5), Cor.VERMELHO));
	}
	
	private static String nome(String nome) {
		return nome;
	}
	
	private static double comprimento(double comprimento) {
		return comprimento;
	}
	
	private static double velocidade(double velocidade) {
		return velocidade;
	}
}
