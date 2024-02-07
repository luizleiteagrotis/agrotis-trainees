
package exercicios.aula19;


class Exercicio26{
	public static void main (String args[]) {
		
		int[] A = new int[10];
		char[] B = new char[10];
		
		System.out.print("Vetor A : ");
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 15);
			System.out.print(A[i] + " ");
		}
		
		
		System.out.print("\nVetor B: ");
		for(int i = 0; i< B.length; i++) {
			if(A[i] < 7) {
				B[i] = 'a';
			} else if(A[i] == 7){
				B[i] = 'b';
			} else if(A[i] > 7 && A[i] < 10){
				B[i] = 'c';
			} else if(A[i] == 10) {
				B[i] = 'd';
			} else {
				B[i] = 'e';
			}
			System.out.print(B[i] + " ");
		}
	}
}
