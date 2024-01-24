package exercicios.aula36.exercicio2;

public class Professor {

	private String nome;
	private String departamento;
	private String email;

	public Professor() {

	}

	public Professor(String nome, String departamento, String email) {
		super();
		this.nome = nome;
		this.departamento = departamento;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Professor [nome=");
		builder.append(nome);
		builder.append(", departamento=");
		builder.append(departamento);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
