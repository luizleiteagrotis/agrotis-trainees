
package exercicios.aula19;


class Exercicio36{
	public static void main (String args[]) {
		
		int[] A = new int[15];
		int[] B = new int[15];
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			A[i] = i;
			B[i] = fatorial(A[i]);
			System.out.print(A[i] + " ");
		}
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i< B.length; i++) {
			System.out.print(B[i] + " ");
		}
	}
	
	private static int fatorial(int n) {
		if(n <= 1) {
			return n;
		}
		
		return n*fatorial(n-1);
	}
}
