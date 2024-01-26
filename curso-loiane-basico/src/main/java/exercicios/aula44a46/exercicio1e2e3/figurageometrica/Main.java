package exercicios.aula44a46.exercicio1e2e3.figurageometrica;

import java.util.ArrayList;
import java.util.List;

import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d.Circulo;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d.Quadrado;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura2d.Triangulo;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura3d.Cilindro;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura3d.Cubo;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura3d.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1e2e3.figurageometrica.figura3d.Piramide;

public class Main {
	
	private static List<FiguraGeometrica> figurasGeometricas = new ArrayList<>();
	
	public static void main(String[] args) {
		criarFigurasGeometricas();
		
		figurasGeometricas.forEach((figura) -> {
			System.out.println(figura);
			mostrarAreaVolume(figura);
		});
	}
	
	private static void criarFigurasGeometricas() {
		figurasGeometricas.add(new Circulo(CorFiguraGeometrica.VERMELHO, raio(10)));
		figurasGeometricas.add(new Quadrado(CorFiguraGeometrica.VERDE, aresta(5)));
		figurasGeometricas.add(new Triangulo(CorFiguraGeometrica.AZUL, base(3), altura(7)));
		figurasGeometricas.add(new Cilindro(CorFiguraGeometrica.VERMELHO, raioBase(5), altura(8)));
		figurasGeometricas.add(new Cubo(CorFiguraGeometrica.VERDE, aresta(10)));
		DimensaoSuperficial baseTriangulo = new Quadrado(CorFiguraGeometrica.AZUL, aresta(5));
		figurasGeometricas.add(new Piramide(CorFiguraGeometrica.AZUL, baseTriangulo, altura(10)));
	}
	
	private static double raio(double raio) {
		return raio;
	}
	
	private static double aresta(double aresta) {
		return aresta;
	}
	
	private static double base(double base) {
		return base;
	}
	
	private static double altura(double altura) {
		return altura;
	}
	
	private static double raioBase(double raioBase) {
		return raioBase;
	}
	
	private static void mostrarAreaVolume(FiguraGeometrica figura) {
		if (figura instanceof DimensaoSuperficial) {
			System.out.println("Area: " + ((DimensaoSuperficial) figura).calcularArea());
		} else if (figura instanceof DimensaoVolumetrica) {
			System.out.println("Volume: " + ((DimensaoVolumetrica)figura).calcularVolume());
		}
		System.out.println();
	}
}
