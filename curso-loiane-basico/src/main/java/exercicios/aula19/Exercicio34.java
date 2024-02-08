
package exercicios.aula19;


class Exercicio34{
	public static void main (String args[]) {
		
		int[] A = new int[10];
	
		for(int i = 0; i< A.length; i++) {
			
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print("Divisores de " + A[i] + ": ");
			for(int j = 1; j <= A[i]; j++) {
				if(A[i]%j == 0) {
					System.out.print(j + " ");
				}
			}
			System.out.print("\n");
		}
	}
}
