package exercicios.aula47a52.exercicio1;

public class Contato {
	private Long id = 1L;
	private String nome;
	private String telefone;

	public Contato() {

	}

	public Contato(String nome, String telefone) {
		this.id = id++;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Long getId() {
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

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
	}

}
