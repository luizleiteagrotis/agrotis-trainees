package exercicios.aula25a27.exercicio3;

import java.util.List;

public class Matricula {
	
	private Curso curso;
	
	public Matricula(Curso curso) {
		this.curso = curso;
	}
	
	public List<Disciplina> getDisciplinas() {
		return curso.getDisciplinas();
	}
}
