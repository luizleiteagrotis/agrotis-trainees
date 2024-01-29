package exercicios.aula25a33;

import java.util.Scanner;

public class Aluno2 {
		
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Aluno aluno = new Aluno();
		
		aluno.entrarComNome();
		aluno.entrarComMatricula();
		aluno.entrarComCurso();
		aluno.entrarComTresDisciplinas();
		aluno.entrarComAsTresNotas();
		aluno.verificarReprovado();
		aluno.verificarReprovado2();
		aluno.verificarReprovado3();
	
	}
}
