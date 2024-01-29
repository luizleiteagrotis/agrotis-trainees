package exercicios.aula36a43.exercicio2;

public abstract class Pessoa {
    private String nome;
    private double rendaBruta;

    public Pessoa(String nome, double rendaBruta) {
        super();
        this.nome = nome;
        this.rendaBruta = rendaBruta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getRendaBruta() {
        return rendaBruta;
    }

    public void setRendaBruta(double rendaBruta) {
        this.rendaBruta = rendaBruta;
    }

    public abstract double calcularImposto();

}
