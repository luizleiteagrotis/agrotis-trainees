package exercicios.aula36a43.exercicio3;

public class Mamifero extends Animal {

    private String alimento;

    public Mamifero() {
    };

    public Mamifero(String nome, double comprimento, String cor, double velocidade) {
        super(nome, comprimento, cor, velocidade);
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
        return "[\n Animal=" + getNome() + "\n comprimento=" + getComprimento() + " cm \n qtdPatas=" + getQtdPatas() + "\n cor="
                        + getCor() + "\n ambiente=" + getAmbiente() + "\n velocidade=" + getVelocidade() + " m/s "
                        + " \n alimento= " + this.alimento + "]\n";
    }
}
