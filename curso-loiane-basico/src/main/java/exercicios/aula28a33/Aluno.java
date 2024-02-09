package exercicios.aula28a33;

public class Aluno {
	
	private String nome;
	private int matricula;
	private String curso;
	private String[] disciplinaArray;
	private int[] notas;
	
	
	
	public Aluno(String nome, int matricula, String curso, String[] disciplinaArray, int[] notas) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.curso = curso;
		this.disciplinaArray = disciplinaArray;
		this.notas = notas;
	}

	public Aluno() {
		this.disciplinaArray = new String[3];
		this.notas = new int[3];
	}
	
	public String verificaNota(String disciplina) {
		if(disciplina.equals(disciplinaArray[0])){
			return (notas[0] >= 7) ? "APROVADO" : "REPROVADO";
		}
		if(disciplina.equals(disciplinaArray[1])) {
			return (notas[1] >= 7) ? "APROVADO" : "REPROVADO";
		} 
		if(disciplina.equals(disciplinaArray[2])) {
			return (notas[2] >= 7) ? "APROVADO" : "REPROVADO";
		}
		return "";
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String[] getDisciplinaArray() {
		return disciplinaArray;
	}

	public void setDisciplinaArray(String[] disciplinaArray) {
		this.disciplinaArray = disciplinaArray;
	}

	public int[] getNotas() {
		return notas;
	}

	public void setNotas(int[] notas) {
		this.notas = notas;
	}
	
}
