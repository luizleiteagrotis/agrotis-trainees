
package exercicios.aula19;


class Exercicio5{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5,6,7,8,9,10};
		int[] B = new int[10];
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			B[i] = A[i] * i;
		}
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(B[i] + " ");
		}
	}
}
