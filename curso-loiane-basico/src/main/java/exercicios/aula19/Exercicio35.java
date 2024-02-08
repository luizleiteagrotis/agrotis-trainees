
package exercicios.aula19;


class Exercicio35{
	public static void main (String args[]) {
		
		int[] A = new int[11];
	
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			
			A[i] = (int) Math.pow(2, i);
			System.out.print(A[i] + " ");
		}
	}
}
