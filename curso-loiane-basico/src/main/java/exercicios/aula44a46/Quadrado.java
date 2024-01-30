package atividades44_46;

public class Quadrado extends Figura2D implements DimensaoSuperficial {

    private double lado;

    public Quadrado() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Quadrado(String nome, String cor, double lado) {
        super(nome, cor);
        this.lado = lado;
    }

    public Quadrado(double lado) {
        super();
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        double area = lado * lado;

        return area;
    }

    @Override
    public String toString() {
        return "Quadrado [lado=" + lado + ", calcularArea()=" + calcularArea() + "]";
    }

}
