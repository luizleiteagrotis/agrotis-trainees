package exercicios.aula25a27.exercicio3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Aluno aluno = new Aluno();
		String[] nomeDisciplinas = new String[3];
		
		aluno.setNome("Gustavo");
		
		for (int i = 0; i < aluno.getDisciplinas().length; i++) {
			System.out.println("Disciplina: " + (i + 1) );
			String nomeDisciplina = scan.next();
			nomeDisciplinas[i] = nomeDisciplina;
		} 
		aluno.setDisciplinas(nomeDisciplinas);
		
		// Passagem de parametro por referencia
		double[][] notasAluno = aluno.getNotas(); 
		String[] disciplina = aluno.getDisciplinas();
		
		for (int i = 0; i < notasAluno.length; i++) {
			System.out.println(disciplina[i]);
			for (int j = 0; j < notasAluno[i].length; j++) {
				System.out.println("Nota " + ( j + 1 ) );
				double nota = scan.nextDouble();
				notasAluno[i][j] = nota;
			}
		}
		
		aluno.verificarAprovado();
		
	}

}
