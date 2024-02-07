
package exercicios.aula19;


class Exercicio16{
	public static void main (String args[]) {
		
		int[] A = {1,2,15,4,5,6,7,17,9,15};
		int somaInf = 0;
		int somaEq = 0;
		int somaSup = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if(A[i] < 15) {
				somaInf += A[i];
			} else if(A[i] == 15) {
				somaEq += A[i];
			} else {
				somaSup += A[i];
			}

		}
		
		System.out.println("\nSoma números inferiores a 15: " + somaInf);
		System.out.println("Soma números iguais a 15: " + somaEq);
		System.out.println("Soma números superiores a 15: " + somaSup);
	}
}
