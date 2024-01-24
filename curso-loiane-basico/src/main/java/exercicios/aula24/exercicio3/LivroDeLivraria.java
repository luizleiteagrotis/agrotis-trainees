package exercicios.aula24.exercicio3;

import exercicios.aula24.exercicio2.Livro;

public class LivroDeLivraria extends Livro {

    private Double valor;
    private String sessao;

    public LivroDeLivraria() {
    }

    public LivroDeLivraria(Double valor, String sessao) {
        super();
        this.valor = valor;
        this.sessao = sessao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

}
