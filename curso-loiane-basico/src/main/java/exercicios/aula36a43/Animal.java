package atividades37_43;

public class Animal {

    private String nome;
    private double comprimento;
    private int patas;
    private String cor;
    private String ambiente;
    private double velocidade;

    public Animal() {
        this.patas = 4;
    }

    public Animal(String nome, double comprimento, String cor, String ambiente, double velocidade) {
        super();
        this.nome = nome;
        this.comprimento = comprimento;
        this.cor = cor;
        this.ambiente = ambiente;
        this.velocidade = velocidade;
        this.setPatas(4);
    }

    public Animal(String nome, double comprimento, double velocidade) {
        super();
        this.nome = nome;
        this.comprimento = comprimento;
        this.velocidade = velocidade;
        this.setPatas(4);
    }

    public Animal(String nome, double comprimento, String cor, double velocidade) {
        super();
        this.nome = nome;
        this.comprimento = comprimento;
        this.cor = cor;
        this.velocidade = velocidade;
        this.setPatas(4);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public int getPatas() {
        return patas;
    }

    public void setPatas(int patas) {
        this.patas = patas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public String toString() {
        return "Animal [nome=" + nome + ", comprimento=" + comprimento + ", patas=" + patas + ", cor=" + cor + ", ambiente="
                        + ambiente + ", velocidade=" + velocidade + ", getNome()=" + getNome() + ", getComprimento()="
                        + getComprimento() + ", getPatas()=" + getPatas() + ", getCor()=" + getCor() + ", getAmbiente()="
                        + getAmbiente() + ", getVelocidade()=" + getVelocidade() + ", getClass()=" + getClass() + ", hashCode()="
                        + hashCode() + ", toString()=" + super.toString() + "]";
    }

    public void imprimirAnimal() {
        System.out.println("______________________");
        System.out.println();
        System.out.println("Nome: " + nome);
        System.out.println("Comprimento: " + comprimento + " metros");
        System.out.println("Patas: " + patas);
        System.out.println("Cor: " + cor);
        System.out.println("Ambiente: " + ambiente);
        System.out.println("Velocidade: " + velocidade + " m/s");

    }

}
