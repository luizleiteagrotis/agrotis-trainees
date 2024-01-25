package exercicios.aula44a46.exercicio1.figuras2D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.Figura2D;

public class Quadrado extends Figura2D implements DimensaoSuperficial {
	private Double lado;
	private Double area;

	public Quadrado(double lado) {
		this.lado = lado;
	}

	@Override
	public void calcularArea() {
		this.area = Math.pow(lado, 2);
		System.out.println("Area do quadrado"+ this.area);
	}

}
