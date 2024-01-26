package exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d;

import exercicios.aula44a46.exercicio1e2e3.figurageometrica.CorFiguraGeometrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.NomeFiguraGemotetrica;

public class Quadrado extends Figura2D implements DimensaoSuperficial{

	private double aresta;
	
	public Quadrado(CorFiguraGeometrica cor, double aresta) {
		super(NomeFiguraGemotetrica.QUADRADO, cor);
		this.aresta = aresta;
	}
	
	@Override
	public double calcularArea() {
		return Math.pow(aresta, 2);
	}
	
	
	@Override
	public String toString() {
		return super.toString()
				+ "\nAresta: " + aresta;
	}
}
