package atividades28_33;

import java.util.Scanner;

public class AlunoTeste {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Aluno aluno = new Aluno();

        aluno.definirCaracterísticas();
        aluno.mostrarSituacaoAluno();

        System.out.println("Digite o número da disciplina para ver o resultado: ");
        aluno.verificarAprovacao((scan.nextInt() - 1));

        scan.close();

    }

}
