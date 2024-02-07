
package exercicios.aula19;


class Exercicio14{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5,6,7,8,9,10};
		int soma = 0;
		int n = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if(A[i]%2 != 0) {
				soma += A[i];
				n++;
			}

		}
		
		double media = soma / n;
		System.out.println("\nMédia números ímpares: " + media);
	}
}
