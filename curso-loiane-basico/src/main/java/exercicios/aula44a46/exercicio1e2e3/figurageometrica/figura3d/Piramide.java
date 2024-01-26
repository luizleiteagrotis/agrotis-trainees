package exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura3d;

import exercicios.aula44a46.exercicio1e2e3.figurageometrica.CorFiguraGeometrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.NomeFiguraGemotetrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d.Figura2D;

public class Piramide extends Figura3D implements DimensaoVolumetrica {

	private DimensaoSuperficial base;
	private double altura;
	
	public Piramide(CorFiguraGeometrica cor, DimensaoSuperficial base, double altura) {
		super(NomeFiguraGemotetrica.PIRAMIDE, cor);
		this.base = base;
		this.altura = altura;
	}
	
	@Override
	public double calcularVolume() {
		return base.calcularArea()*altura / 3;
	}

	@Override
	public String toString() {
		return super.toString()
				+ "\nBase:" 
				+ "\n--------------------\n"
				+ base
				+ "\nArea: " + base.calcularArea()
				+ "\n--------------------"
				+ "\nAltura: " + altura;
	}
}
