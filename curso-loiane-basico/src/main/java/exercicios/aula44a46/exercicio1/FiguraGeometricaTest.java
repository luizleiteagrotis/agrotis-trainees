package exercicios.aula44a46.exercicio1;

import exercicios.aula44a46.exercicio1.figuras2D.Circulo;
import exercicios.aula44a46.exercicio1.figuras2D.Quadrado;
import exercicios.aula44a46.exercicio1.figuras2D.Triangulo;
import exercicios.aula44a46.exercicio1.figuras3D.Cilindro;
import exercicios.aula44a46.exercicio1.figuras3D.Cubo;
import exercicios.aula44a46.exercicio1.figuras3D.Piramide;

public class FiguraGeometricaTest {
	public static void main(String[] args) {

		Figura2D circulo = new Circulo();
		Figura2D quadrado = new Quadrado(2.0);
		Figura2D triangulo = new Triangulo();
		Figura3D cilindro = new Cilindro();
		Figura3D cubo = new Cubo();
		Figura3D piramide = new Piramide(2.0, 2.0, 2.0);

		FiguraGeometrica[] figurasGeometricas = { circulo, quadrado, triangulo, cilindro, cubo, piramide };
		
	    for (FiguraGeometrica figura : figurasGeometricas) {
	    	
            }

            System.out.println();

	}
}
