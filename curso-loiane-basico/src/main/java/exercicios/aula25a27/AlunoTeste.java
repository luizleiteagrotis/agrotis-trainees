

package exercicios.aula25a27;

import java.util.Scanner;

public class AlunoTeste {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Aluno aluno = new Aluno();
		
		aluno.nome = "Pedro";
		aluno.matricula = 123;
		aluno.curso = "Engenharia";
		
		aluno.disciplinaArray[0] = "Oficinas de Integração II";
		aluno.disciplinaArray[1] = "Sistemas Inteligentes";
		aluno.disciplinaArray[2] = "Lógica Reprogramável";
		
		aluno.notas[0] = 8;
		aluno.notas[1] = 7;
		aluno.notas[2] = 6;
		
		int idAluno  = 0;
		String nomeAluno = "";
		String cursoAluno = "";
		
		System.out.println("Informe a matrícula do aluno:");
		do {

			idAluno = scan.nextInt();
			if(idAluno != aluno.matricula) {
				System.out.println("Aluno não encontrado! Tente novamente:");
			}
		} while (idAluno != aluno.matricula);
		
		System.out.println("Informe o nome do aluno:");
		do {
			nomeAluno = scan.next().toLowerCase();
			if(!nomeAluno.equals(aluno.nome.toLowerCase())) {
				System.out.println("Aluno não encontrado! Tente novamente:");
			}
		} while (!nomeAluno.equals(aluno.nome.toLowerCase()));
		
		System.out.println("Informe o curso do aluno:");
		do {
			scan.nextLine(); 
			cursoAluno = scan.nextLine().toLowerCase();
			if(!cursoAluno.equals(aluno.curso.toLowerCase())) {
				System.out.println("Aluno não encontrado! Tente novamente:");
			}
		} while (!cursoAluno.equals(aluno.curso.toLowerCase()));
		
		for(int i = 0; i < aluno.disciplinaArray.length; i++) {
			System.out.println(aluno.disciplinaArray[i] + " - Nota: " + aluno.notas[i] + " " + aluno.verificaNota(aluno.disciplinaArray[i]));
		}
		
		
	}
	

}