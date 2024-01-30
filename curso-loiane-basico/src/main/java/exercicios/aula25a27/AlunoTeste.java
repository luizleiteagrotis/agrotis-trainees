package atividades25_27;

import java.util.Scanner;

public class AlunoTeste {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Aluno aluno = new Aluno();

        System.out.println("Nome do aluno: ");
        aluno.setNome(scan.next());

        System.out.println("Nome do curso: ");
        aluno.setCurso(scan.next());

        System.out.println("Número da matrícula: ");
        aluno.setMatricula(scan.nextInt());

        for (int i = 0; i < aluno.disciplinas.length; i++) {
            System.out.println("Disciplina " + (i + 1) + ": ");
            aluno.disciplinas[i] = scan.next();
        }

        for (int i = 0; i < aluno.notasDisciplinas.length; i++) {
            System.out.println("Digite a nota da disciplina " + aluno.disciplinas[i]);
            for (int j = 0; j < aluno.notasDisciplinas[i].length; j++) {
                System.out.print("Entre a " + (j + 1) + "ª nota: ");
                aluno.notasDisciplinas[i][j] = scan.nextDouble();
            }
        }

        aluno.mostrarSituacaoAluno();

        System.out.println("Consulte no menu mostrado anteriormente o número da disciplina.");
        System.out.println("Digite o número da disciplina para ver o resultado: ");
        aluno.verificarAprovacao((scan.nextInt() - 1));

        scan.close();

    }

}
