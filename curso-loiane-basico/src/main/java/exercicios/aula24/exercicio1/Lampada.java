package exercicios.aula24.exercicio1;

public class Lampada {

    private String marca;
    private Double valor;
    private String voltagem;

    public Lampada() {
    }

    public Lampada(String marca, Double valor, String voltagem) {
        this.marca = marca;
        this.valor = valor;
        this.voltagem = voltagem;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    @Override
    public String toString() {
        return "Lampada [marca=" + marca + ", valor=" + valor + ", voltagem=" + voltagem + "]";
    }

}
