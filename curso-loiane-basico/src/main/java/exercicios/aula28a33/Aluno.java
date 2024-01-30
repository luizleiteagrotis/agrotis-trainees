package atividades28_33;

import java.util.Scanner;

public class Aluno {

    private String nome;
    private int matricula;
    private String curso;
    private String[] disciplinas = new String[3];
    double[][] notasDisciplinas = new double[3][4];

    public Aluno() {
        super();
    }

    public Aluno(String nome, int matricula, String curso, String[] disciplinas, double[][] notasDisciplinas) {
        super();
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinas = disciplinas;
        this.notasDisciplinas = notasDisciplinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void definirCaracterísticas() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Cadastro de aluno");
        System.out.print("Digite o nome: ");
        this.setNome(scan.next());
        System.out.print("Digite o número da matrícula: ");
        this.setMatricula(scan.nextInt());
        System.out.print("Digite o nome do curso: ");
        this.setCurso(scan.next());

        for (int i = 0; i < disciplinas.length; i++) {
            System.out.println("Disciplina " + (i + 1) + ": ");
            this.disciplinas[i] = scan.next();
            for (int j = 0; j < this.notasDisciplinas[i].length; j++) {
                System.out.print("Entre a " + (j + 1) + "ª nota: ");
                this.notasDisciplinas[i][j] = scan.nextDouble();
            }
        }
        
    

    }

    public void verificarAprovacao(int indiceDisciplina) {
        double soma = 0;

        for (int i = 0; i < this.notasDisciplinas[indiceDisciplina].length; i++) {
            soma += notasDisciplinas[indiceDisciplina][i];
        }

        double media = soma / 4;

        if (media >= 7) {
            System.out.println("Aprovado! Média final: " + media);
        } else {
            System.out.println("Reprovado. Média final: " + media);
        }
    }

    public void mostrarSituacaoAluno() {
        System.out.println();
        System.out.println("Nome do aluno: " + this.nome);
        System.out.println("Matrícula: " + this.matricula);
        System.out.println("Curso: " + this.curso);

        for (int i = 0; i < notasDisciplinas.length; i++) {
            System.out.println("Notas da disciplina " + (i + 1) + ": " + this.disciplinas[i]);
            for (int j = 0; j < notasDisciplinas[i].length; j++) {
                System.out.println((j + 1) + "ª nota: " + this.notasDisciplinas[i][j]);
            }
        }
        System.out.println();
    }

}
