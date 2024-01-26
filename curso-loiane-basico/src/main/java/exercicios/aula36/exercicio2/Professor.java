package exercicios.aula36.exercicio2;

public class Professor {

	private String nome;
	private String departamento;
	private String email;
	
	public Professor(String nome, String departamento, String email) {
		this.nome = nome;
		this.departamento = departamento;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\tDepartamento: " + departamento + "\tEmail: " + email;
	}
}
