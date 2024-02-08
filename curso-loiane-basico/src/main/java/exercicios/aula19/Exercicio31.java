
package exercicios.aula19;


class Exercicio31{
	public static void main (String args[]) {
		
		int[] A = new int[5];
		
		System.out.print("Vetor A : ");
		for(int i = 0; i< A.length; i++) {
			
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
			
		}
		
		System.out.println("\nTabuada : ");
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println(A[i] + " X " + j + " = " + A[i]*j);
			}
			System.out.println("");
		}
	}
}
