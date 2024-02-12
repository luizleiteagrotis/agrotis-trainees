package exercicios.aula35;

public class Exercicio2 {

	public static void main(String[] args) {
		System.out.println(somatorio(11));

	}
	
	public static int somatorio(int n) {
		if(n == 0) {
			return n;
		}
		return n + somatorio(n-1);
	}



}
