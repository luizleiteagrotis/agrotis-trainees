
package exercicios.aula19;


class Exercicio32{
	public static void main (String args[]) {
		
		int[] A = new int[5];
		
		System.out.println("Vetor A : ");
		for(int i = 0; i< A.length; i++) {
			
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.println(A[i] + " " + (isPrime(A[i]) ? "PRIMO" : "NÃO PRIMO"));
			
		}
	}
	
	private static boolean isPrime(int n) {
		if( n <= 2 && n > 0) {
			return true;
		}
		
		for(int i = 2; i <= n/2; i++) {
			if((n%i) == 0) {
				return false;
			}
		}
		return true;
		
	}
}
