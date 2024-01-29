package exercicios.aula47a52;

public class ContatoNaoExisteException extends Exception {
	  
	private String nomeDoContato;
	
	public ContatoNaoExisteException (String nomeDoContato) {
		this.nomeDoContato = nomeDoContato;
	}
	
	public String getMessage() {
		return " O " +nomeDoContato+" nao foi encontrado em sua agenda";
		
	}
	
}
