
package exercicios.aula19;


class Exercicio17{
	public static void main (String args[]) {
		
		int[] A = {23,46,15,12,28,36,10,17,39,15};
		int count = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if(A[i] > 35) {
				count++;
			}

		}
		
		System.out.println("\nPessoas com mais de 35 anos: " + count);

	}
}
