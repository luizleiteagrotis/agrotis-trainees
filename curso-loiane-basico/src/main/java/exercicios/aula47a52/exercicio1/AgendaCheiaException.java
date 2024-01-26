package exercicios.aula47a52.exercicio1;

public class AgendaCheiaException extends Exception {

	@Override
	public String getMessage() {
		return "A agenda já está cheia";
	}
}
