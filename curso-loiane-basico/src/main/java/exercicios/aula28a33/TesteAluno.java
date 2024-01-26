package exercicios.aula28a33;

import java.util.List;
import java.util.Scanner;

import exercicios.aula25a27.Aluno;

public class TesteAluno {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Insira o nome do aluno.");
		String nomeDoAluno = sc.next();
		
		Aluno novoAluno = cadastrarAluno(nomeDoAluno, sc);
		double media = novoAluno.calcularMediaNotas();
		novoAluno.alunoAprovado(media);
		sc.close();
	}
	private static Aluno cadastrarAluno(String nomeDoAluno, Scanner sc) {
		Aluno novoAluno = new Aluno();
		novoAluno.setNome(nomeDoAluno);
		System.out.println("Informe 3 matérias que está matriculado.");
		List<String> materiasCursando = receberMaterias(sc);
		novoAluno.materiasCursando(materiasCursando);
		receberNotas(sc, novoAluno, materiasCursando);
		return novoAluno;
	}
	private static List<String> receberMaterias(Scanner sc) {
		System.out.println("Matéria 1:");
		String materiaUm = sc.next();
		System.out.println("Matéria 2:");
		String materiaDois = sc.next();
		System.out.println("Matéria 3:");
		String materiaTres = sc.next();
		return List.of(materiaUm, materiaDois, materiaTres);
	}
	private static void receberNotas(Scanner sc, Aluno aluno, List<String> materiasCursando) {
		for (String materia : materiasCursando) {
			System.out.println("Informe a nota para a matéria " + materia + ":");
			int nota1 = sc.nextInt();
			System.out.println("Informe a nota para a matéria " + materia + ":");
			int nota2 = sc.nextInt();
			System.out.println("Informe a nota para a matéria " + materia + ":");
			int nota3 = sc.nextInt();
			aluno.adicionaNota(materia, nota1);
			aluno.adicionaNota(materia, nota2);
			aluno.adicionaNota(materia, nota3);
		}
	}

	
}

