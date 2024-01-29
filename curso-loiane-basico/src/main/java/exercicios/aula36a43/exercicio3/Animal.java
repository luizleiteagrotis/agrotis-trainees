package exercicios.aula36a43.exercicio3;

public class Animal {
    private String nome;
    private double comprimento;
    private int qtdPatas;
    private String cor;
    private String ambiente;
    private double velocidade;

    public Animal() {
    }

    public Animal(String nome, double comprimento, int qtdPatas, String cor, double velocidade) {
        super();
        this.nome = nome;
        this.comprimento = comprimento;
        this.qtdPatas = qtdPatas;
        this.cor = cor;
        this.velocidade = velocidade;
    }

    public Animal(String nome, double comprimento, int qtdPatas, double velocidade) {
        super();
        this.nome = nome;
        this.comprimento = comprimento;
        this.qtdPatas = qtdPatas;
        this.velocidade = velocidade;
    }

    public Animal(String nome, double comprimento, String cor, double velocidade) {
        super();
        this.nome = nome;
        this.comprimento = comprimento;
        this.qtdPatas = 4;
        this.cor = cor;
        this.velocidade = velocidade;
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

    public int getQtdPatas() {
        return qtdPatas;
    }

    public void setQtdPatas(int qtdPatas) {
        this.qtdPatas = qtdPatas;
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
        return "[Animal=" + nome + "\n comprimento=" + comprimento + " cm \n qtdPatas=" + qtdPatas + "\n cor=" + cor
                        + "\n ambiente=" + ambiente + "\n velocidade=" + velocidade + " m/s ]";
    }

}
