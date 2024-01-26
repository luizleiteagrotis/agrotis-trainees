package exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d;

import exercicios.aula44a46.exercicio1e2e3.figurageometrica.CorFiguraGeometrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.NomeFiguraGemotetrica;

public class Triangulo extends Figura2D implements DimensaoSuperficial{

	private double base;
	private double altura;
	
	public Triangulo(CorFiguraGeometrica cor, double base, double altura) {
		super(NomeFiguraGemotetrica.TRIANGULO, cor);
		this.base = base;
		this.altura = altura;
	}
	
	@Override
	public double calcularArea() {
		return base*altura / 2;
	}

	@Override
	public String toString() {
		return super.toString()
				+ "\nBase: " + base
				+ "\nAltura: " + altura;
	}
}
