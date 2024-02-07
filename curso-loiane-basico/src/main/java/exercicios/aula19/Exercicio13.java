
package exercicios.aula19;


class Exercicio13{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5,6,7,8,9,10};
		int soma = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if(A[i]%5 == 0) {
				soma += A[i];
			}

		}
		
		System.out.println("\nSoma múltiplos de 5: " + soma);
	}
}
