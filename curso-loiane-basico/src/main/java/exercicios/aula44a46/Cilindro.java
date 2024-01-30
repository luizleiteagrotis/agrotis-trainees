package atividades44_46;

public class Cilindro extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {

    private double raio;
    private double altura;

    public Cilindro() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Cilindro(double raio, double altura) {
        super();
        this.raio = raio;
        this.altura = altura;
    }

    public Cilindro(String nome, String cor, double raio, double altura) {
        super(nome, cor);
        this.raio = raio;
        this.altura = altura;
    }

    @Override
    public double calcularVolume() {

        double volume = Math.PI * (Math.pow(raio, 2)) * altura;
        return volume;

    }

    @Override
    public double calcularArea() {

        double area = (2 * Math.PI * Math.pow(raio, 2) + (2 * Math.PI * raio * altura));
        return area;

    }

    @Override
    public String toString() {
        return "Cilindro [raio=" + raio + ", altura=" + altura + ", calcularVolume()=" + calcularVolume() + ", calcularArea()="
                        + calcularArea() + "]";
    }

}
