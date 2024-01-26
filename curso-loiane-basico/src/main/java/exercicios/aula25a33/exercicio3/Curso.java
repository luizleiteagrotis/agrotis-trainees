package exercicios.aula25a33.exercicio3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Curso {

	private String nome;
	private List<Disciplina> disciplinas;
	
	public Curso(String nome) {
		this.nome = nome;
		disciplinas = new ArrayList<>();
	}
	
	public void add(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}
	
	public List<Disciplina> getDisciplinas() {
		return Collections.unmodifiableList(disciplinas);
	}
}
