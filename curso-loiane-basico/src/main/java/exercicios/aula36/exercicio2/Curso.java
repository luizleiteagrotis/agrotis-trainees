package exercicios.aula36.exercicio2;

import java.util.Arrays;

public class Curso {

	private String nome;

	private String horario;

	private Professor professor;

	private Aluno[] aluno;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Aluno[] getAluno() {
		return aluno;
	}

	public void setAluno(Aluno[] aluno) {
		this.aluno = aluno;
	}
	
	public double obterMediaTurma() {
		double soma = 0;
		for(Aluno aluno : aluno) {
			if(aluno != null) {
				soma += aluno.calcularMedia();				
			}
		}
		return soma/aluno.length;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Curso [nome=");
		builder.append(nome);
		builder.append(", horario=");
		builder.append(horario);
		builder.append(", professor=");
		builder.append(professor);
		builder.append(", aluno=");
		builder.append(Arrays.toString(aluno));
		builder.append("]");
		return builder.toString();
	}

}
