package exercicios.aula44a46;

public class FiguraGeometricaTeste {

	public static void main(String[] args) {
		Cilindro cilindro = new Cilindro("Cilindro", "Vermelho", 5, 30);
		Cubo cubo = new Cubo("Cubo", "Verde", 6);
		Piramide piramide = new Piramide("Piramide", "Amarela", 18,16);
		
		Quadrado quadrado = new Quadrado("Quadrado", "Laranja", 10);
		Triangulo triangulo = new Triangulo("Triangulo", "Roxo", 8,12);
		Circulo circulo = new Circulo("Circulo", "Azul", 3);
		
		FiguraGeometrica[] figuras = new FiguraGeometrica[6];
		figuras[0] = circulo;
		figuras[1] = triangulo;
		figuras[2] = quadrado;
		figuras[3] = piramide;
		figuras[4] = cubo;
		figuras[5] = cilindro;
		
		for(FiguraGeometrica figura : figuras) {
			if(figura instanceof Figura3D) {
				System.out.println(figura.nome + ": Área = " +  String.format("%.3f",((DimensaoSuperficial) figura).calcularArea()) + " cm²" +
						"; Volume = " +  String.format("%.3f",((DimensaoVolumetrica) figura).calcularVolume()) + " cm³");
			} else {
				System.out.println(figura.nome + ": Área = " +  String.format("%.3f",((DimensaoSuperficial) figura).calcularArea()) + " cm²");
			}
		}
	}

}
