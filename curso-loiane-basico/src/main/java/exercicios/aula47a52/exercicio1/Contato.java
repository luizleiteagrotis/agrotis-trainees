package exercicios.aula47a52.exercicio1;

public class Contato {
	
	private long id;
	private String nome;
	private String telefone;
	
	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Id: " + id + "\tNome: " + nome + "\tTelefone: " + telefone;
	}
}
