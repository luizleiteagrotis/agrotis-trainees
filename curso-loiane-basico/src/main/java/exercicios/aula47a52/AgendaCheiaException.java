package exercicios.aula47a52;

public class AgendaCheiaException extends Exception{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "Agenda cheia!";
	}
}
