package exercicios.aula47a52;

public class ContatoNaoExisteException extends Exception {

	private static final long serialVersionUID = 1L;
	private String nome;
	
	public ContatoNaoExisteException(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Contato " + nome + "não existe";
	}
	
	
}
