package exercicios.aula24.exercicio6;

public class Contato {

	private String nome;
	private String sobreNome;
	private String numeroTelefoneCelular;
	private String numeroTelefoneFixo;
	private String email;

	public Contato() {
	}

	public Contato(String nome, String sobreNome, String numeroTelefoneCelular, String numeroTelefoneFixo,
			String email) {
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.numeroTelefoneCelular = numeroTelefoneCelular;
		this.numeroTelefoneFixo = numeroTelefoneFixo;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getNumeroTelefoneCelular() {
		return numeroTelefoneCelular;
	}

	public void setNumeroTelefoneCelular(String numeroTelefoneCelular) {
		this.numeroTelefoneCelular = numeroTelefoneCelular;
	}

	public String getNumeroTelefoneFixo() {
		return numeroTelefoneFixo;
	}

	public void setNumeroTelefoneFixo(String numeroTelefoneFixo) {
		this.numeroTelefoneFixo = numeroTelefoneFixo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", sobreNome=" + sobreNome + ", numeroTelefoneCelular=" + numeroTelefoneCelular
				+ ", numeroTelefoneFixo=" + numeroTelefoneFixo + ", email=" + email + "]";
	}

}
