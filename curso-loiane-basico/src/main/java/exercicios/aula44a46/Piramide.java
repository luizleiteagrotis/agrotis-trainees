package atividades44_46;

public class Piramide extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {

    private double base;
    private double altura;
    private double perimetroBase = 4 * base;
    private double apotema = base / 2;

    public Piramide() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Piramide(String nome, String cor, double base, double altura) {
        super(nome, cor);
        this.base = base;
        this.altura = altura;
    }

    public Piramide(double base, double altura) {
        super();
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularVolume() {

        double volume = (base * altura) / 3;

        return volume;
    }

    @Override
    public double calcularArea() {

        double area = base + (.5 * perimetroBase * apotema);
        return area;
    }

    @Override
    public String toString() {
        return "Piramide [base=" + base + ", altura=" + altura + ", perimetroBase=" + perimetroBase + ", apotema=" + apotema
                        + ", calcularVolume()=" + calcularVolume() + ", calcularArea()=" + calcularArea() + "]";
    }

}
