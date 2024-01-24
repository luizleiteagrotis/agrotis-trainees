package exercicios.aula25a27.exercicio3;

import java.util.List;

public class Aluno {
	
	private Matricula matricula;
	
	public Aluno(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public List<Disciplina> getDisciplinas() {
		return matricula.getDisciplinas();
	}
}
