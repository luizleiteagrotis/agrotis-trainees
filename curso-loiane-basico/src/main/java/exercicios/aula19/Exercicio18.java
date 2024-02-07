
package exercicios.aula19;


class Exercicio18{
	public static void main (String args[]) {
		
		int[] A = {23,46,15,12,28,36,10,17,39,15};
		int maiorIdade = 0;
		int menorIdade = Integer.MAX_VALUE;
		
		int indexMaior = 0;
		int indexMenor = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if(A[i] > maiorIdade) {
				maiorIdade = A[i];
				indexMaior = i;
			}
			if(A[i] < menorIdade) {
				menorIdade = A[i];
				indexMenor = i;
			}
		}
		
		System.out.println("\nMenor idade: " + menorIdade + " - Índice: " + indexMenor);
		System.out.println("Maior idade: " + maiorIdade + " - Índice: " + indexMaior);
	}
}
