package exercicios.aula47a52.exercicio1;

public class ContatoNaoExisteException extends Exception {

	@Override
	public String getMessage() {
		return "O identificador do contato não existe";
	}
}
