package exercicios.aula28a33.exercicio3;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Aluno aluno = new Aluno();
		
		aluno.setNome("Gustavo");
		
		for (int i = 0; i < aluno.getDisciplinas().length; i++) {
			System.out.println("Disciplina: " + (i + 1) );
			// passnado parametros atraves de metodos proprio da classe
			aluno.setNomeDisciplina(i, scan.next());
		} 
		
		// Passagem de parametro por referencia
		String[] disciplina = aluno.getDisciplinas();
		double[][] notasAluno = aluno.getNotas(); 
		
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
