
package exercicios.aula20;


class Exercicio2{
	public static void main (String args[]) {
		
		int[][] A = new int[10][10];
		
		int maiorLinha = 0;
		int menorLinha = Integer.MAX_VALUE;
		
		int maiorColuna = 0;
		int menorColuna = Integer.MAX_VALUE;
		
	
		System.out.println("Matriz A: ");
		for(int i = 0; i< A.length; i++) {
			for(int j = 0; j < A[i].length; j++) {
				A[i][j] = (int) Math.round(Math.random() * 9);
				System.out.print(A[i][j] + " ");
			}
			System.out.println("");
		}
		
		for(int i = 0; i < A.length; i++) {
			if(A[i][6] > maiorColuna) {
				maiorColuna = A[i][6];
			}
			if(A[i][6] < menorColuna) {
				menorColuna = A[i][6];
			}
		}
		
		for(int j = 0; j < A.length; j++) {
			if(A[4][j] > maiorLinha) {
				maiorLinha = A[4][j];
			}
			if(A[4][j] < menorLinha) {
				menorLinha = A[4][j];
			}
		}
		
		System.out.println("Maior valor coluna 7: " + maiorColuna + " Menor valor coluna 7: " + menorColuna);
		System.out.println("Maior valor linha 5: " + maiorLinha + " Menor valor linha 5: " + menorLinha);
	}
}
