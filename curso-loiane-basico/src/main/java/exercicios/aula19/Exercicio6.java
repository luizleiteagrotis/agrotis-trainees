
package exercicios.aula19;


class Exercicio6{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5,6,7,8,9,10};
		int[] B = A;
		int[] C = new int[10];
		
		System.out.print("Vetor A e B: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			C[i] = A[i] + B[i];
		}
		
		System.out.print("\nVetor C: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(C[i] + " ");
		}
	}
}
