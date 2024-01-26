package exercicios.aula47a52.exercicio1;

public class Contato {

	private String nome;
	private String telefone;
	private int identificador;

	private static int contador;

	public Contato() {
		identificador = ++contador;
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

	public int getIdentificador() {
		return identificador;
	}

	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", telefone=" + telefone + ", identificador=" + identificador + "]";
	}

}
