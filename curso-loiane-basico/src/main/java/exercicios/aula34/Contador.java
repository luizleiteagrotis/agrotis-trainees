package exercicios.aula34;

public class Contador {

	private static int count;
	
	public Contador() {
		count++;
	}
	
	public static void incrementar() {
		count++;
	}
	
	public static void zerar() {
		count = 0;
	}
	
	public static int getCount() {
		return count;
	}
}
