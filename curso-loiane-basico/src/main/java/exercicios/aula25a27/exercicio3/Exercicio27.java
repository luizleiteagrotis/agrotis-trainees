package exercicios.aula25a27.exercicio3;

import java.util.Scanner;

import exercicios.aula25a27.exercicio1.Lampada;
import exercicios.aula25a27.exercicio2.ContaCorrente;

public class Exercicio27 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        // teste exercicio 1
        Lampada lamp = new Lampada();
        lamp.ligar();
        System.out.println(lamp.estado);
        System.out.println("------------");

        // teste exercicio 2
        ContaCorrente cont = new ContaCorrente();
        cont.saldo = 1000;

        cont.consultarSaldo();
        cont.depositar(100);

        cont.consultarSaldo();
        cont.depositar(-10);

        cont.consultarSaldo();

        cont.sacar(100);
        cont.consultarSaldo();

        cont.sacar(1001);
        cont.consultarSaldo();
        cont.consultarCheque();

        System.out.println("---------------");

        // teste exercicio 3
        Aluno aluno = new Aluno();
        System.out.println("Digite seu nome: ");
        aluno.setNome(scan.next());

        System.out.println("Digite sua matricula: ");
        aluno.setMatricula(scan.next());

        System.out.println("Digite seu curso: ");
        aluno.setCurso(scan.next());

        System.out.println("Digite a quantidade de disciplinas desejadas para fazer a consulta: ");
        int qtdDisciplinas = scan.nextInt();
        String[] disciplinas = new String[qtdDisciplinas];
        double[] notas = new double[qtdDisciplinas];
        scan.nextLine();

        for (int i = 0; i < disciplinas.length; i++) {
            System.out.println("Digite a disciplina: ");
            disciplinas[i] = scan.nextLine();
            System.out.println("Digite a nota: ");
            notas[i] = scan.nextDouble();
            scan.nextLine();
        }
        aluno.setDisciplinas(disciplinas);
        aluno.setNotas(notas);
        aluno.mostrarAprovacao();
    }

}
