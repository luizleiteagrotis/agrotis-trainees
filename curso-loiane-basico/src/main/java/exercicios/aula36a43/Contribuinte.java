package atividades37_43;

public class Contribuinte {

    private String nome;
    private double rendaBruta;

    public Contribuinte(String nome, double rendaBruta) {
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

    @Override
    public String toString() {
        return "Contribuinte [nome=" + nome + ", getNome()=" + getNome() + ", getClass()=" + getClass() + ", hashCode()="
                        + hashCode() + ", toString()=" + super.toString() + "]";
    }

    public void calcularImposto() {
        // TODO Auto-generated method stub

    }

}
