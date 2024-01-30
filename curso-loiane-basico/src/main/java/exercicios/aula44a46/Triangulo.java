package atividades44_46;

public class Triangulo extends Figura2D implements DimensaoSuperficial {

    private double base;
    private double altura;

    public Triangulo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Triangulo(String nome, String cor, double base, double altura) {
        super(nome, cor);
        this.base = base;
        this.altura = altura;
    }

    public Triangulo(double base, double altura) {
        super();
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        double area = (base * altura) / 2;

        return area;
    }

    @Override
    public String toString() {
        return "Triangulo [base=" + base + ", altura=" + altura + ", calcularArea()=" + calcularArea() + "]";
    }

}
