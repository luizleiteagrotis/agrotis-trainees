package exercicios.aula47a52.exercicio1;

public class AgendaCheiaException extends Exception {
	
	public AgendaCheiaException(Contato contato) {
		super("Agenda cheia! Contato: " + contato);
	}
}
