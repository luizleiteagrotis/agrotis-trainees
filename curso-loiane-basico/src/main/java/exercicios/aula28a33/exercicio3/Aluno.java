package exercicios.aula28a33.exercicio3;

public class Aluno {

    private String nome;
    private String matricula;
    private String curso;
    private String[] disciplinas;
    private double[] notas;

    public Aluno() {

    }

    public Aluno(String nome, String matricula, String curso, String[] disciplinas, double[] notas) {
        super();
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinas = disciplinas;
        this.notas = notas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() < 2) {

        } else {
            this.nome = nome;
        }

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(String[] disciplinas) {
        this.disciplinas = disciplinas;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    public void mostrarAprovacao() {
        double[] notas = this.getNotas();
        String[] disciplinas = this.getDisciplinas();
        String estado = "";
        for (int i = 0; i < this.getNotas().length; i++) {
            if (notas[i] < 7) {
                estado = "Reprovado";
            } else {
                estado = "Aprovado";
            }
            System.out.println(disciplinas[i] + " " + notas[i] + " " + estado);
        }

    }

}