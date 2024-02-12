package exercicios.aula36;

public class Contato {

	private String nome;
	private String email;
	private int telefone;
	
	

	public Contato(String nome, String email, int telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Contato() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getTelefone() {
		return telefone;
	}
	
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Contato [nome = " + nome + ", email = " + email + ", telefone = " + telefone + "]";
	}
}
