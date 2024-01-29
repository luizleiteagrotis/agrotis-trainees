package exercicios.aula24.exercicio4;

import java.util.Date;

import exercicios.aula24.exercicio2.Livro;

public class LivroDeBiblioteca extends Livro {
    int id;
    public Date dataEmprestimo;
    Date dataDevolucao;
    public String nomeCliente;
    String sessao;
    public boolean emprestimo = false;
}
