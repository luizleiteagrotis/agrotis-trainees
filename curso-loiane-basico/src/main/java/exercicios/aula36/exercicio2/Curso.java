package exercicios.aula36.exercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Curso {
	
	private String nome;
	private String data;
	private Professor professor;
	private List<Aluno> alunos;
	
	public Curso(String nome, String data, Professor professor) {
		this.nome = nome;
		this.data = data;
		this.professor = professor;
		alunos = new ArrayList<Aluno>();
	}
	
	public void adicionar(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public List<Aluno> getAlunos() {
		return Collections.unmodifiableList(alunos);
	}

	public String getNome() {
		return nome;
	}

	public String getData() {
		return data;
	}

	public Professor getProfessor() {
		return professor;
	}
}
