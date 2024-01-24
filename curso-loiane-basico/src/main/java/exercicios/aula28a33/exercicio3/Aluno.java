package exercicios.aula28a33.exercicio3;

public class Aluno {

	private String nome;

	private String matricula;

	private String curso;

	private String[] disciplinas = new String[3];

	private double[][] notas = new double[3][4];

	private double soma;

	public Aluno() {}
 	
	public Aluno(String nome, String matricula, String curso, String[] disciplinas, double[][] notas, double soma) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.curso = curso;
		this.disciplinas = disciplinas;
		this.notas = notas;
		this.soma = soma;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double getSoma() {
		return soma;
	}

	public void setSoma(double soma) {
		this.soma = soma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String[] getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(String[] disciplinas) {
		this.disciplinas = disciplinas;
	}

	public double[][] getNotas() {
		return notas;
	}

	public void setNotas(double[][] notas) {
		this.notas = notas;
	}

	public void verificarAprovado() {
		for (int i = 0; i < notas.length; i++) {
			System.out.println(disciplinas[i]);
			for (int j = 0; j < notas[i].length; j++) {
				System.out.print(notas[i][j] + " ");
				soma += notas[i][j];
			}
			if (soma / 4 >= 7) {
				System.out.println(nome + " está aprovado com média: " + (soma / 4));
			} else {
				System.out.println(nome + " está reprovado com média: " + (soma / 4));
			}
			soma = 0;
		}

	}
	
	public void setNomeDisciplina(int index, String nomeDisciplina) {
		this.disciplinas[index] = nomeDisciplina;
	}

}
