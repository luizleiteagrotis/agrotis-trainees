package exercicios.aula44a46.exercicio1;

import exercicios.aula44a46.exercicio1.figuras2D.Circulo;
import exercicios.aula44a46.exercicio1.figuras2D.Quadrado;
import exercicios.aula44a46.exercicio1.figuras2D.Triangulo;
import exercicios.aula44a46.exercicio1.figuras3D.Cilindro;
import exercicios.aula44a46.exercicio1.figuras3D.Cubo;
import exercicios.aula44a46.exercicio1.figuras3D.Piramide;

public class FiguraGeometricaTest {
	public static void main(String[] args) {

		Circulo circulo = new Circulo(5.0);
		circulo.calcularArea();
		circulo.calcularVolume();
		Quadrado quadrado = new Quadrado(2.0);
		quadrado.calcularArea();
		Triangulo triangulo = new Triangulo(2.0, 3.0);
		triangulo.calcularArea();
		Cilindro cilindro = new Cilindro(3.0, 3.0);
		cilindro.calcularArea();
		cilindro.calcularVolume();
		Cubo cubo = new Cubo(5.0);
		cubo.calcularArea();
		cubo.calcularVolume();
		Piramide piramide = new Piramide(2.0, 2.0, 2.0);
		piramide.calcularArea();
		piramide.calcularVolume();

		FiguraGeometrica[] figurasGeometricas = { circulo, quadrado, triangulo, cilindro, cubo, piramide };

		for (FiguraGeometrica figura : figurasGeometricas) {
			System.out.println(figura);

		}

		System.out.println();

	}
}
