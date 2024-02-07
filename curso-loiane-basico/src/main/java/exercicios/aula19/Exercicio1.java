
package exercicios.aula19;


class Exercicio1{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5};
		int[] B = A;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
		}
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(B[i] + " ");
		}
	}
}
