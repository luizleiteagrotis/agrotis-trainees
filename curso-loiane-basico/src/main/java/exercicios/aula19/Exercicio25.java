
package exercicios.aula19;


class Exercicio25{
	public static void main (String args[]) {
		
		int[] A = new int[10];
		int[] B = new int[10];
		int[] C = new int[10];
		
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
		for(int i = 0; i< C.length; i++) {
			if(A[i] > B[i]) {
				C[i] = 1;
			} else if(A[i] == B[i]){
				C[i] = 0;
			} else {
				C[i] = -1;
			}
			System.out.print(C[i] + " ");
		}
	}
}
