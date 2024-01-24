package exercicios.aula25a27.exercicio3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String nomeCurso = "Ciencia da Computacao";
		Curso curso = new Curso(nomeCurso);
		curso.add(new Disciplina("Programacao imperativa"));
		curso.add(new Disciplina("Compiladores e interpretadores"));
		curso.add(new Disciplina("Complexidade de algoritmos"));
		
		Matricula matricula = new Matricula(curso);
		
		Aluno aluno = new Aluno(matricula);
		
		Scanner scanner = new Scanner(System.in);
		
		adicionarNotasDisciplinas(aluno, scanner);
		
		System.out.println();
		
		mostrarResultadoDisciplinas(aluno);
	}

	private static void adicionarNotasDisciplinas(Aluno aluno, Scanner scanner) {
		for (Disciplina disciplina : aluno.getDisciplinas()) {
			System.out.println(disciplina.getNome());
			do {
				double nota = pegarNota(scanner);
				disciplina.addNota(nota);
				System.out.print("Deseja continuar adicionando notas? (true ou false): ");
			} while (scanner.nextBoolean());
			System.out.println();
		}
	}

	private static double pegarNota(Scanner scanner) {
		Double nota = null;
		do {
			System.out.print("Informe uma nota de 0 a 10: ");
			nota = scanner.nextDouble();
			if (nota < 0 || nota > 10) {
				System.out.println("Nota invalida!");
			}
		} while (nota == null || (nota < 0 || nota > 10));
		return nota;
	}
	
	private static void mostrarResultadoDisciplinas(Aluno aluno) {
		for (Disciplina disciplina : aluno.getDisciplinas()) {
			System.out.println(disciplina.getNome());
			double media = disciplina.calcularMedia();
			System.out.println("Notas: " + disciplina.getNotas());
			System.out.println("Media: " + media);
			if (media < 7) {
				System.out.println("Situacao: Reprovado");
			} else {
				System.out.println("Situacao: Aprovado");
			}
			System.out.println();
		}
	}
}
