package exercicios.aula34.exercicio1;

public class Contador {
	
	private static int numeroIntancias = 0;
	
	public Contador() {
		incrementar();
	}
	
	public static void zerar() {
		numeroIntancias = 0;
	}
	
	public static void incrementar() {
		numeroIntancias++;
	}
	
	public static void decrementar() {
		numeroIntancias--;
	}
	
	public static int getNumeroIntancias() {
		return numeroIntancias;
	}
}
