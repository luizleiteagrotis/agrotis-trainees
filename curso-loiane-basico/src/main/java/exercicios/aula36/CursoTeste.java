package exercicios.aula36;

import java.util.Scanner;

public class CursoTeste {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Curso curso = new Curso("Java Básico","10:20");
		
		Professor professor = new Professor("Agnaldo", "DAINF", "agnaldo@gmail.com");
		
		curso.setProfessor(professor);
		
		Aluno[] alunos = new Aluno[5];
		
		alunos[0] = new Aluno("Zacarias", 123);
		alunos[1] = new Aluno("Maycon", 132);
		alunos[2] = new Aluno("Esthephany", 213);
		alunos[3] = new Aluno("Turquesa", 312);
		alunos[4] = new Aluno("Esmeraldo", 124);
		
		curso.setAlunos(alunos);
		
		int mediaTurma = 0;
		
		for(Aluno aluno : alunos) {
			int[] notas = new int[4];
			int somaNotaAluno  = 0;
			
			System.out.println("Insira as notas do aluno " + aluno.toString() + ": ");
			for (int i = 0; i < notas.length; i++) {
				notas[i] = scan.nextInt();
				somaNotaAluno += notas[i];
			}
			aluno.setNotas(notas);
			int mediaAluno = somaNotaAluno/4; 
			System.out.println("Media: " + mediaAluno + (mediaAluno >=7 ? " - Aprovado(a)" : " - Reprovado(a)"));
			mediaTurma += mediaAluno;
			
		}
		
		mediaTurma = mediaTurma / curso.getAlunos().length;
		System.out.println("Média da Turma: " + mediaTurma );
		
		scan.close();
	}

}
