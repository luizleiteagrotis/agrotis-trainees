package exercicios.aula34.exercicio1;

public class Contador {
	
	private static int valorHora;
	
	public Contador() {
	}
	
	public static void zerarValor() {
		valorHora = 0;
		retornarValor();
	}
	
	public static void incrementarValor() {
		valorHora++;
		retornarValor();
	}
	
	public static  void retornarValor() {
		System.out.println(valorHora);
	}

}
