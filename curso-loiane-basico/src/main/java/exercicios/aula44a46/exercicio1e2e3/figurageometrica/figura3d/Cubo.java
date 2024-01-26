package exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura3d;

import exercicios.aula44a46.exercicio1e2e3.figurageometrica.CorFiguraGeometrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.NomeFiguraGemotetrica;

public class Cubo extends Figura3D implements DimensaoVolumetrica{

	private double aresta;
	
	public Cubo(CorFiguraGeometrica cor, double aresta) {
		super(NomeFiguraGemotetrica.CUBO , cor);
		this.aresta = aresta;
	}
	
	@Override
	public double calcularVolume() {
		return Math.pow(aresta, 3);
	}

	@Override
	public String toString() {
		return super.toString()
				+ "\nAresta: " + aresta;
	}
}
