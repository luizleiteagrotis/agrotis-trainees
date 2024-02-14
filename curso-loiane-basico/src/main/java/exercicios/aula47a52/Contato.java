package exercicios.aula47a52;

public class Contato {

	private int id;
	private String nome;
	private String telefone;
	
	public Contato() {
		this.id = id ++;
	};
	
	public Contato(String nome, String telefone) {
		this.id = id++;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
