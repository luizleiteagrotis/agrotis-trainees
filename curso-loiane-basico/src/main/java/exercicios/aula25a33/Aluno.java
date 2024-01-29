package exercicios.aula25a33;

import java.util.Scanner;

public class Aluno {
	
	Scanner sc = new Scanner(System.in);
	
	String nome;
	int matricula;
	String curso;
	String nomeDisciplina1;
	String nomeDisciplina2;
	String nomeDisciplina3;
	double notaDisciplina1;
	double notaDisciplina2;
	double notaDisciplina3;
	
	void entrarComNome() {
		System.out.println("Entre com o seu nome: ");
		nome = sc.nextLine();
	}

	void entrarComMatricula() {
		System.out.println("Entre com a sua matricula: ");
		matricula = sc.nextInt();
	}
	
	void entrarComCurso() {
		System.out.println("Entre com o nome do curso: ");
		curso = sc.next();
	}
	
	void entrarComTresDisciplinas() {
		System.out.println("Entre com o nome da PRIMEIRA disciplina: ");
		nomeDisciplina1 = sc.next();
		System.out.println();
		System.out.println("Entre com o nome da SEGUNDA disciplina: ");
		nomeDisciplina2 = sc.next();
		System.out.println();
		System.out.println("Entre com o nome da TERCEIRA disciplina: ");
		nomeDisciplina3 = sc.next();
		System.out.println();
	}
	
	void entrarComAsTresNotas() {
		System.out.println("Entre com a nota da PRIMEIRA disciplina: ");
		notaDisciplina1 = sc.nextDouble();
		System.out.println("Entre com a nota da SEGUNDA disciplina: ");
		notaDisciplina2 = sc.nextDouble();
		System.out.println("Entre com a nota da TERCEIRA disciplina: ");
		notaDisciplina3 = sc.nextDouble();
	}
	
	double verificarReprovado() {
		if (notaDisciplina1 < 7.0){
			System.out.println("Você reprovou na Disciplina 1");
		} else {
			System.out.println("Você foi aprovado na Disciplina 1");
		}
	return notaDisciplina1;
	}
	
	double verificarReprovado2() {

		if (notaDisciplina2 < 7.0){
			System.out.println("Você reprovou na Disciplina 2");
		} else {
			System.out.println("Você foi aprovado na Disciplina 2");
		}
	return notaDisciplina2;
	}
	
	double verificarReprovado3() {
		if (notaDisciplina3 < 7.0){
			System.out.println("Você reprovou na Disciplina 3");
		} else {
			System.out.println("Você foi aprovado na Disciplina 3");
		}
	return notaDisciplina3;
	}
	
}
