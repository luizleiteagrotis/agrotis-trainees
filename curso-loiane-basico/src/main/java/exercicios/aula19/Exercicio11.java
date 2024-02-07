
package exercicios.aula19;


class Exercicio11{
	public static void main (String args[]) {
		
		int[] A = {1,2,3,4,5,6,7,8,9,10};
		int par = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if( A[i]%2 == 0) {
				par ++;
			}
		}
		
		System.out.println("Quantidade de pares: " + par);
	}
}
