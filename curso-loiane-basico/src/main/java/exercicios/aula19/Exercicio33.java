
package exercicios.aula19;


class Exercicio33{
	public static void main (String args[]) {
		
		int[] A = new int[10];
	
		for(int i = 0; i< A.length; i++) {
			
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print("Pares até " + A[i] + ": ");
			for(int j = 0; j < A[i]; j+=2) {
				System.out.print(j + " ");
			}
			System.out.print("\n");
		}
	}
}
