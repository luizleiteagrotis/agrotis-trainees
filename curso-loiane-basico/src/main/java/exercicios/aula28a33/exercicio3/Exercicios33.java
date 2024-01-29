package exercicios.aula28a33.exercicio3;

import java.util.Scanner;

import exercicios.aula28a33.exercicio1.Lampada;
import exercicios.aula28a33.exercicio2.ContaCorrente;

public class Exercicios33 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        // teste exercicio 1
        Lampada lamp = new Lampada();
        System.out.println(lamp.getEstado());
        lamp.ligar();
        System.out.println(lamp.getEstado());
        lamp.desligar();
        System.out.println(lamp.getEstado());

        System.out.println(lamp.getGarantiaMeses());

        System.out.println("---------------");

        // teste exercicio 2
        ContaCorrente conta = new ContaCorrente("Matheus Lourenço");
        conta.mostrarInformacoes();
        conta.sacar(500);
        conta.mostrarInformacoes();
        conta.depositar(1000);
        conta.mostrarInformacoes();
        conta.sacar(501);
        conta.mostrarInformacoes();
        System.out.println("----------------");

        // teste exercicio 3
        System.out.println("Digite seu nome: ");
        String nome = scan.nextLine();

        System.out.println("Digite sua matricula: ");
        String matricula = scan.nextLine();

        System.out.println("Digite seu curso: ");
        String curso = scan.nextLine();

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

        Aluno aluno = new Aluno(nome, matricula, curso, disciplinas, notas);

        aluno.mostrarAprovacao();
    }

}
