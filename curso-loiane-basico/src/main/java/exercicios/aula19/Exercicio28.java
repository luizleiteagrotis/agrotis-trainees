
package exercicios.aula19;


class Exercicio28{
	public static void main (String args[]) {
		
		int[] A = new int[10];
		int[] B = new int[10];
		int[] C = new int[20];
		
		System.out.print("Vetor A : ");
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
		}
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i< B.length; i++) {
			B[i] = (int) Math.round(Math.random() * 10);
			System.out.print(B[i] + " ");
		}
		
		System.out.print("\nVetor C: ");
		for(int i = 0; i< A.length; i++) {
			C[i] = A[i];
			C[i + A.length ] = B[i];
		}
		
		for(int i = 0; i< C.length; i++) {
			System.out.print(C[i] + " ");
		}
	}
}
