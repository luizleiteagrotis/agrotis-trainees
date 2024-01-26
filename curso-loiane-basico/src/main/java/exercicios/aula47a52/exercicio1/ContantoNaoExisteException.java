package exercicios.aula47a52.exercicio1;

public class ContantoNaoExisteException extends Exception {
	
	public ContantoNaoExisteException(long idContato) {
		super("Nao existe contado com id " + idContato);
	}
}
