package atividades44_46;

public class FiguraGeometrica {

    private String nome;
    private String cor;

    public FiguraGeometrica() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FiguraGeometrica(String nome, String cor) {
        super();
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "FiguraGeometrica [nome=" + nome + ", cor=" + cor + "]";
    }

}
