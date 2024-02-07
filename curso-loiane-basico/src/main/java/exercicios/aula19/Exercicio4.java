
package exercicios.aula19;


class Exercicio4{
	public static void main (String args[]) {
		
		int[] A = {1,4,9,16,25,36,49,64,81,100,121,144,169,196,225};
		double[] B = new double[15];
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			B[i] = Math.sqrt(A[i]);
		}
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(String.format("%.1f", B[i]) + " ");
		}
	}
}
