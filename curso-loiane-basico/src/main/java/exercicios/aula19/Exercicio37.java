
package exercicios.aula19;


class Exercicio37{
	public static void main (String args[]) {
		
		int[] A = new int[10];
		int[] B = new int[10];
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			A[i] = i;
			System.out.print(A[i] + " ");
		}
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i < B.length; i++) {
			for(int j = i; j < B.length; j++) {
				B[i] += A[j];
			}
			System.out.print(B[i] + " ");
		}
	}
	
}
