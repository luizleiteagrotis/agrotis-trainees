package exercicios.aula36.exercicio2;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Professor professor = criarProfessor();
		Curso curso = criarCurso(professor);
		adicionarAlunos(curso);
		adicionarNotas(curso);
		mostrarResultados(curso);
	}

	private static Professor criarProfessor() {
		String nomeProfessor = "Grande professor";
		String departamentoProfessor = "Departamento dos professores";
		String emailProfessor = "professor@gmail.com";
		Professor professor = new Professor(nomeProfessor, departamentoProfessor, emailProfessor);
		return professor;
	}
	
	private static Curso criarCurso(Professor professor) {
		String nomeCurso = "Java";
		String horarioCurso = "09:00";
		Curso curso = new Curso(nomeCurso, horarioCurso, professor);
		return curso;
	}
	
	private static void adicionarAlunos(Curso curso) {
		for (int i = 1; i < 6; i++) {
			String nomeAluno = "aluno" + i;
			String matriculaAluno = "matricula" + i;
			curso.adicionar(new Aluno(nomeAluno, matriculaAluno));
		}
	}
	
	private static void adicionarNotas(Curso curso) {
		List<Aluno> alunosCurso = curso.getAlunos();
		alunosCurso.forEach((aluno) -> {
			adicionarNotas(aluno);
			System.out.println();
		});
	}

	private static void adicionarNotas(Aluno aluno) {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < aluno.getQuantidadeNotas(); i++) {
			System.out.println(aluno);
			System.out.print("Digite uma nota: ");
			double nota = scanner.nextDouble();
			aluno.adicionar(nota);
		}
	}
	
	private static void mostrarResultados(Curso curso) {
		System.out.println("Curso: " + curso.getNome());
		System.out.println("Professor -> " + curso.getProfessor());
		List<Aluno> alunosCurso = curso.getAlunos();
		alunosCurso.forEach((aluno) -> {
			System.out.print(aluno);
			double media = aluno.calcularMedia();
			System.out.print("\tMedia: " + media);
			System.out.print("\tAprovado: " + (media >= 7));
			System.out.println();
		});
	}
}
