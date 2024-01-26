package exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d;

import exercicios.aula44a46.exercicio1e2e3.figurageometrica.CorFiguraGeometrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.NomeFiguraGemotetrica;

public class Circulo extends Figura2D implements DimensaoSuperficial {

	private double raio;
	
	public Circulo(CorFiguraGeometrica cor, double raio) {
		super(NomeFiguraGemotetrica.CIRCULO, cor);
		this.raio = raio;
	}
	
	@Override
	public double calcularArea() {
		return Math.PI * Math.pow(raio, 2);
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ "\nRaio: " + raio;
	}
}
