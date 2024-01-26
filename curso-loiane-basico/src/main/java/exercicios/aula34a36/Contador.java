package exercicios.aula34a36;

public class Contador {
	
	    public static int contadorEstatico = 0;

	    public Contador() {
	        incrementarContador();
	    }

	    public static void zerarContador() {
	        contadorEstatico = 0;
	    }

	    public static void incrementarContador() {
	        contadorEstatico++;
	    }

	    public static int obterValorContador() {
	        return contadorEstatico;
	    }
	

}
