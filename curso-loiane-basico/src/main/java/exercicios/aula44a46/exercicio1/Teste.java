package exercicios.aula44a46.exercicio1;

import exercicios.aula44a46.exercicio1.figura2D.*;
import exercicios.aula44a46.exercicio1.figura3D.*;
import exercicios.aula44a46.exercicio1.interfaces.Figura2D;
import exercicios.aula44a46.exercicio1.interfaces.Figura3D;
import exercicios.aula44a46.exercicio1.interfaces.FiguraGeometrica;


public class Teste {

	public static void main(String[] args) {
			Quadrado quadrado = new Quadrado();
			quadrado.setLado(10);
			
			Circulo circulo = new Circulo();
			circulo.setRaio(2);
			
			Triangulo triangulo = new Triangulo();
			triangulo.setAltura(10);
			triangulo.setBase(10);
			
					
			Cilindro cilindro = new Cilindro();
			cilindro.setRaio(2);
			cilindro.setAltura(3);
			
			Cubo cubo = new Cubo();
			cubo.setAresta(5);
			
			Piramide piramide = new Piramide();
			piramide.setAltura(3);
			piramide.setApotema(4);
			piramide.setArestaBase(2);
			piramide.setNumPoliBase(4);
			piramide.setBase(quadrado);
			
			FiguraGeometrica[] figuras = new FiguraGeometrica[6];
			figuras[0]=quadrado;
			figuras[1]=circulo;
			figuras[2]=triangulo;
			figuras[3]=cilindro;
			figuras[4]=cubo;
			figuras[5]=piramide;
			
			for(FiguraGeometrica figura : figuras) {
				if(figura instanceof Figura2D) {
					Figura2D f2d = (Figura2D) figura;
					System.out.println(f2d.calcularArea());
				}
				
				if(figura instanceof Figura3D) {
					Figura3D f3d = (Figura3D) figura;
					System.out.println(f3d.calcularArea());			
					System.out.println(f3d.calcularVolume());			
				}
			}
			
	}

}
