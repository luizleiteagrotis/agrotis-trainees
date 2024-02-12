package exercicios.aula36;

public class Aluno {

	private String nome;
	private int matricula;
	private int[] notas;
	
	
	public Aluno(String nome, int matricula) {
		super();
		this.nome = nome;
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public int[] getNotas() {
		return notas;
	}
	
	public void setNotas(int[] notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return  nome + ", Matricula: " + matricula;
	}
	
}
