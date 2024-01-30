package atividades44_46;

public class Cubo extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {

    private double lado;

    public Cubo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Cubo(double lado) {
        super();
        this.lado = lado;
    }

    public Cubo(String nome, String cor, double lado) {
        super(nome, cor);
        this.lado = lado;
    }

    @Override
    public double calcularVolume() {

        double volume = Math.pow(lado, 3);
        return volume;

    }

    @Override
    public double calcularArea() {

        double area = 6 * (Math.pow(lado, 2));
        return area;

    }

    @Override
    public String toString() {
        return "Cubo [lado=" + lado + ", calcularVolume()=" + calcularVolume() + ", calcularArea()=" + calcularArea() + "]";
    }

}
