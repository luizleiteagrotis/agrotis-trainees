package exercicios.aula44a46.exercicio1;

import exercicios.aula44a46.exercicio1.figuras2D.Circulo;
import exercicios.aula44a46.exercicio1.figuras2D.Quadrado;
import exercicios.aula44a46.exercicio1.figuras2D.Triangulo;
import exercicios.aula44a46.exercicio1.figuras3D.Cilindro;
import exercicios.aula44a46.exercicio1.figuras3D.Cubo;
import exercicios.aula44a46.exercicio1.figuras3D.Piramide;

public class FiguraGeometricaTest {
	public static void main(String[] args) {

		Figura2D circulo = new Circulo(5.0);
		circulo.calcularArea();
		Figura2D quadrado = new Quadrado(2.0);
		quadrado.calcularArea();
		Figura2D triangulo = new Triangulo(2.0, 3.0);
		triangulo.calcularArea();
		Figura3D cilindro = new Cilindro(3.0, 3.0);
		cilindro.calcularArea();
		cilindro.calcularVolume();
		Figura3D cubo = new Cubo(5.0);
		cubo.calcularArea();
		cubo.calcularVolume();
		Figura3D piramide = new Piramide(2.0, 2.0, 2.0);
		piramide.calcularArea();
		piramide.calcularVolume();

		FiguraGeometrica[] figurasGeometricas = { circulo, quadrado, triangulo, cilindro, cubo, piramide };

		for (FiguraGeometrica figura : figurasGeometricas) {

			System.out.println(figura);
		}

		System.out.println();

	}
}
