
package exercicios.aula19;


class Exercicio30{
	public static void main (String args[]) {
		
		int[] A = new int[20];
		int[] B = new int[20];
		
		int k = 10;
		int j = 0;
		
		System.out.print("Vetor A : ");
		for(int i = 0; i< A.length; i++) {
			
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
			
		}
		
		System.out.print("\nVetor B : ");
		for(int i = 0; i < A.length; i++) {
			if(A[i]%2 == 0 ) {
				B[j] = A[i];
				j++;
			} 
		}
		
		for(int i = 0; i < B.length/2; i++) {
			if(A[i]%2 != 0 ) {
				B[k] = A[i];
				k++;
			} 
		}
		
		for(int i = 0; i< B.length; i++) {
			System.out.print(B[i] + " ");
		}
	}
}
