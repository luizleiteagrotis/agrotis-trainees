package exercicios.aula36.exercicio2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Curso curso = new Curso();
		Aluno[] alunos = new Aluno[3];
		Professor professor = new Professor("Fabio", "Portugues-ingles", "fabio@ifc.com");

		curso.setHorario("meio dia");
		curso.setNome("Ingles");
		curso.setProfessor(professor);

		for (int i = 0; i < alunos.length; i++) {
			Aluno aluno = new Aluno();
			double[] notas = aluno.getNotas();
			System.out.println("Entre com o nome e matricula do aluno: " + (i + 1));
			aluno.setNome(scan.nextLine());
			aluno.setMatricula(scan.next());

			for (int j = 0; j < aluno.getNotas().length; j++) {
				System.out.println("Nota: " + (j + 1));
				notas[j] = scan.nextDouble();
				scan.nextLine();
			}
			aluno.setNotas(notas);
			System.out.println(aluno);
			alunos[i] = aluno;
		}
		curso.setAluno(alunos);

		for (int i = 0; i < alunos.length; i++) {
			double media = alunos[i].calcularMedia();
			System.out
					.println(media >= 7 ? "Média " + media + " Está aprovado" : " Média " + media + " Está reprovado");

		}

		System.out.println(professor);
		System.out.println(curso);
		System.out.println(curso.obterMediaTurma());

	}
}
