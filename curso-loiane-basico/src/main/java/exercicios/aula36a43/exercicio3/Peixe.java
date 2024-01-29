package exercicios.aula36a43.exercicio3;

public class Peixe extends Animal {
    private String caracteristicas;

    public Peixe() {

    }

    public Peixe(String nome, double comprimento, int qtdPatas, String cor, double velocidade, String caracteristica) {
        super(nome, comprimento, qtdPatas, cor, velocidade);
        this.setAmbiente("Mar");
        this.setCor("Cinzento");
        this.caracteristicas = caracteristica;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "[\n Animal=" + getNome() + "\n comprimento=" + getComprimento() + " cm \n qtdPatas=" + getQtdPatas() + "\n cor="
                        + getCor() + "\n ambiente=" + getAmbiente() + "\n velocidade=" + getVelocidade() + " m/s "
                        + " \n caracteristica= " + this.caracteristicas + "\n]";
    }

}
