package atividades44_46;

public class Circulo extends Figura2D implements DimensaoSuperficial {

    private double raio;

    public Circulo(double raio) {
        super();
        this.raio = raio;
    }

    public Circulo(String nome, String cor, double raio) {
        super(nome, cor);
        this.raio = raio;
    }

    @Override
    public double calcularArea() {

        double area = Math.PI * (Math.pow(raio, 2));
        return area;

    }

    @Override
    public String toString() {
        return "Circulo [raio=" + raio + ", calcularArea()=" + calcularArea() + "]";
    }

}
