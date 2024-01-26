package exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura3d;

import exercicios.aula44a46.exercicio1e2e3.figurageometrica.CorFiguraGeometrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.NomeFiguraGemotetrica;

public class Cilindro extends Figura3D implements DimensaoVolumetrica{

	private double raioBase;
	private double altura;
	
	public Cilindro(CorFiguraGeometrica cor, double raioBase, double altura) {
		super(NomeFiguraGemotetrica.CILINDRO, cor);
		this.raioBase = raioBase;
		this.altura = altura;
	}
	
	@Override
	public double calcularVolume() {
		return Math.PI * Math.pow(raioBase, 2) * altura;
	}

	@Override
	public String toString() {
		return super.toString()
				+ "\nRaioBase: " + raioBase
				+ "\nAltura: " + altura;
	}
}
