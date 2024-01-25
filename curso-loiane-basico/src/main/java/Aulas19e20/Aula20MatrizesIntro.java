package Aulas19e20;

public class Aula20MatrizesIntro {

	public static void main(String[] args) {
		
		//Instanciar
		double[][] notasAlunos = new double[4][4];
		
		notasAlunos[0][0] = 10.0;
		notasAlunos[0][1] = 9.5;
		notasAlunos[0][2] = 6.5;
		notasAlunos[0][3] = 7.0;
		
		notasAlunos[1][0] = 3.5;
		notasAlunos[1][1] = 5.3;
		notasAlunos[1][2] = 9.8;
		notasAlunos[1][3] = 10.0;
		
		notasAlunos[2][0] = 6.5;
		notasAlunos[2][1] = 6.3;
		notasAlunos[2][2] = 7.8;
		notasAlunos[2][3] = 7.7;
		
		notasAlunos[3][0] = 9.0;
		notasAlunos[3][1] = 5.7;
		notasAlunos[3][2] = 8.8;
		notasAlunos[3][3] = 5.3;
				
		//imprimindo matriz na tela
		
		//(alterar nota)
		//notasAlunos[1][3] = 8;
		
		System.out.println();
		
		for (int i = 0; i<notasAlunos.length; i++) {
			for (int j=0; j<notasAlunos[i].length; j++) {
				System.out.print(notasAlunos[i][j] + " - ");
			}
			System.out.println();
		}
		
		System.out.println("Calculando a média de cada aluno");
		
		double soma;
		for (int i=0; i<notasAlunos.length; i++) {
			
			soma = 0;
			for (int j=0; j<notasAlunos.length; j++) {
				soma += notasAlunos[i][j];
				
			}
			System.out.printf("Média do aluno " + i + " é = " + "%.1f%n", (soma/4));
		}
		//mudando notas
		
		double[] notasAluno1 = {7, 8, 9, 10};
		double[][] notasAluno2 = {{7, 8, 9, 10}, {8, 6, 7, 10}};
		
		System.out.println("Output da matriz notasAluno2");
		
		for (int i=0; i<notasAluno2.length; i++) {
			for (int j=0; j<notasAluno2.length; j++) {
				System.out.print(notasAluno2[i][j] + " - ");
			}
		}
	}
}