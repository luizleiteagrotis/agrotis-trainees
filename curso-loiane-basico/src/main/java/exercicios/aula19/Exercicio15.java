
package exercicios.aula19;


class Exercicio15{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5,6,7,8,9,10};
		int pares = 0;
		int impares = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if(A[i]%2 == 0) {
				pares++;
			} else {
				impares++;
			}

		}
		
		int size = A.length;
		double percentualPar = (double) pares/ (double) size * 100;
		double percentualImpar = (double) impares/ (double) size * 100;
		
 		System.out.println("\nPercentual números pares: " + percentualPar + "%");
		System.out.println("Percentual números ímpares: " + percentualImpar + "%");
	}
}