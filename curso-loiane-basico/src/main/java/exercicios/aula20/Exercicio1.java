
package exercicios.aula20;


class Exercicio1{
	public static void main (String args[]) {
		
		int[][] A = new int[4][4];
		
		int maior = 0;
		int linha = 0;
		int coluna = 0;
	
		System.out.println("Matriz A: ");
		for(int i = 0; i< A.length; i++) {
			for(int j = 0; j < A[i].length; j++) {
				A[i][j] = (int) Math.round(Math.random() * 9);
				if(A[i][j] > maior) {
					maior = A[i][j];
					linha = i;
					coluna = j;
				}
				System.out.print(A[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("Maior valor: " + maior + " Linha: " + linha + " Coluna: " + coluna);
	}
}
