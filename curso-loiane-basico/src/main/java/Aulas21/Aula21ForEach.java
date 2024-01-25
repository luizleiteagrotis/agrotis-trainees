package Aulas21;

import java.util.Random;

public class Aula21ForEach {

	public static void main(String[] args) {
			
		int[] notas = new int[10];

		Random random = new Random ();
		
		for (int i=0; i<notas.length; i++){
			System.out.printf(random.nextInt(10) + "%n");
		}
		
		System.out.printf("Usando for each" + "%n");
		
		for (int nota : notas){
			System.out.println(random.nextInt(10));
		}
		
		double[][] notasAlunos = new double [4][4];
		
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

		for (double[] notasAluno : notasAlunos){
			for (double nota : notasAluno){
				System.out.print(nota + " ");
			}
			System.out.println();
		}
	}
}