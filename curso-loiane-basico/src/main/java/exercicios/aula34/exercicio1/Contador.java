package exercicios.aula34.exercicio1;

public class Contador {

	private static int contador;

	public Contador() {
		contador++;
	}

	public void zerarContador() {
		contador = 0;
	}

	public void incementarContador() {
		contador++;
	}

	public int retornaContador() {
		return contador;
	}

	

}
