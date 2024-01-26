package exercicios.aula44a46.exercicio1.figuras2D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.Figura2D;

public class Circulo extends Figura2D implements DimensaoSuperficial{

	private Double raio;
	private Double pi = Math.PI;
	private Double area;

	public Circulo(Double raio) {
		this.raio = raio;
	}

	@Override
	public void calcularArea() {
		this.area = Math.PI * raio * raio;
	}

	@Override
	public String toString() {
		return "Circulo [raio=" + raio + ", pi=" + pi + ", area=" + area + "]";
	}

	
}
