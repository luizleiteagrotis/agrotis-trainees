package exercicios.aula44a46.exercicio1.figuras2D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.Figura2D;

public class Triangulo extends Figura2D implements DimensaoSuperficial {
	private Double base;
	private Double altura;

	@Override
	public void calcularArea() {
		Double area = 0.5 * base * altura;
		System.out.println("Área do triângulo: " + area);
	}

}
