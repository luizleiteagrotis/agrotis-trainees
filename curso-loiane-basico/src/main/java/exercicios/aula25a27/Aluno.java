package atividades25_27;

public class Aluno {

    String nome;
    private int matricula;
    private String curso;
    String[] disciplinas = new String[3];
    double[][] notasDisciplinas = new double[3][4];

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

    // public String[] getDisciplinas() {
    // return disciplinas;
    // }
    //
    // public void setDisciplinas(String[] disciplinas) {
    // this.disciplinas = disciplinas;
    // }
    //
    // public double[][] getNotasDisciplinas() {
    // return notasDisciplinas;
    // }
    //
    // public void setNotasDisciplinas(double[][] notasDisciplinas) {
    // this.notasDisciplinas = notasDisciplinas;
    // }

    public Aluno() {
        super();
        // TODO Auto-generated constructor stub
    }

    public double verificarAprovacao(int indiceDisciplina) {

        double soma = 0;

        for (int i = 0; i < this.notasDisciplinas[indiceDisciplina].length; i++) {
            soma += notasDisciplinas[indiceDisciplina][i];
        }

        double media = soma / 4;

        if (media >= 7) {
            System.out.print("Aprovado! Média final: " + media);
            return media;
        } else {
            System.out.print("Reprovado. Média final:" + media);
            return media;
        }
    }

    public void mostrarSituacaoAluno() {
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
