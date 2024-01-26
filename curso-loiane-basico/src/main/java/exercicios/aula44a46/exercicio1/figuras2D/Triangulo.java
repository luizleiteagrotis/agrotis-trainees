package exercicios.aula44a46.exercicio1.figuras2D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.Figura2D;

public class Triangulo extends Figura2D implements DimensaoSuperficial {
	private Double base;
	private Double altura;
	private Double area;

	public Triangulo(Double base, Double altura) {
		this.base = base;
		this.altura = altura;
	}

	@Override
	public void calcularArea() {
		area = 0.5 * base * altura;
		System.out.println("Área do triângulo: " + area);
	}

	@Override
	public String toString() {
		return "Triangulo [base=" + base + ", altura=" + altura + " area=" + area;
	}

}
