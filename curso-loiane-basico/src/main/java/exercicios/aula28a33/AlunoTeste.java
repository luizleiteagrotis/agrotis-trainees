

package exercicios.aula28a33;

import java.util.Scanner;

public class AlunoTeste {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		
		String[] disciplinaArray = new String[3];
		
		disciplinaArray[0] = "Oficinas de Integração II";
		disciplinaArray[1] = "Sistemas Inteligentes";
		disciplinaArray[2] = "Lógica Reprogramável";
		
		int[] notas = new int [3];
		notas[0] = 8;
		notas[1] = 7;
		notas[2] = 6;
		
		Aluno aluno = new Aluno("Pedro", 123, "Engenharia", disciplinaArray, notas);
		
		int idAluno  = 0;
		String nomeAluno = "";
		String cursoAluno = "";
		
		System.out.println("Informe a matrícula do aluno:");
		do {

			idAluno = scan.nextInt();
			if(idAluno != aluno.getMatricula()) {
				System.out.println("Aluno não encontrado! Tente novamente:");
			}
		} while (idAluno != aluno.getMatricula());
		
		System.out.println("Informe o nome do aluno:");
		do {
			nomeAluno = scan.next().toLowerCase();
			if(!nomeAluno.equals(aluno.getNome().toLowerCase())) {
				System.out.println("Aluno não encontrado! Tente novamente:");
			}
		} while (!nomeAluno.equals(aluno.getNome().toLowerCase()));
		
		System.out.println("Informe o curso do aluno:");
		do {
			scan.nextLine(); 
			cursoAluno = scan.nextLine().toLowerCase();
			if(!cursoAluno.equals(aluno.getCurso().toLowerCase())) {
				System.out.println("Aluno não encontrado! Tente novamente:");
			}
		} while (!cursoAluno.equals(aluno.getCurso().toLowerCase()));
		
		for(int i = 0; i < aluno.getDisciplinaArray().length; i++) {
			System.out.println(aluno.getDisciplinaArray()[i] + " - Nota: " + aluno.getNotas()[i] + " " + aluno.verificaNota(aluno.getDisciplinaArray()[i]));
		}
		
		
	}
	

}
