package atividades37_43;

public class Mamifero extends Animal {

    private String alimento;

    public Mamifero() {
        this.setAmbiente("Terra");
    }

    public Mamifero(String alimento) {
        this.alimento = alimento;
    }

    public Mamifero(String nome, double comprimento, String cor, double velocidade, String alimento) {
        super(nome, comprimento, cor, velocidade);
        this.alimento = alimento;
        this.setPatas(4);
        this.setAmbiente("Terra");
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    @Override
    public String toString() {
        return "Mamifero [alimento=" + alimento + ", getAlimento()=" + getAlimento() + ", getNome()=" + getNome()
                        + ", getComprimento()=" + getComprimento() + ", getPatas()=" + getPatas() + ", getCor()=" + getCor()
                        + ", getAmbiente()=" + getAmbiente() + ", getVelocidade()=" + getVelocidade() + ", toString()="
                        + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }

    @Override
    public void imprimirAnimal() {
        super.imprimirAnimal();
        System.out.println("Alimento: " + alimento);
    }

}
