
package exercicios.aula19;


class Exercicio3{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int[] B = new int[15];
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			B[i] = A[i] * A[i];
		}
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(B[i] + " ");
		}
	}
}
