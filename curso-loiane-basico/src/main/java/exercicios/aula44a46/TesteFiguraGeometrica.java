package exercicios.aula44a46;

public class TesteFiguraGeometrica {

	public static void main(String[] args) {
		
		Cilindro first = new Cilindro ();
		first.setAlturaCilindro(0);
		first.setRaioCilindro(0);
		
		Circulo two = new Circulo ();
		two.setRaioCirculo(0);
		
		Cubo three = new Cubo ();
		three.setLadoCubo(0);

		Piramide four = new Piramide ();
		four.setAlturaPiramide(0);
		four.setApotema(0);
		four.setBasePiramide(two);
		four.setLadoBasePiramide(0);
		four.setPoligonos(0);
		
		Quadrado five = new Quadrado ();
		five.setLadoQuadrado(0);
		
		Triangulo six = new Triangulo ();
		six.setAlturaTriangulo(0);
		six.setBaseTriangulo(0);

		FiguraGeometrica[] geometrica = new FiguraGeometrica[6];
		
		geometrica[0] = first;
		geometrica[1] = two;
		geometrica[2] = three;
		geometrica[3] = four;
		geometrica[4] = five;
		geometrica[5] = six;
		
		for (FiguraGeometrica figuras : geometrica) {
		
			if(figuras instanceof Figura2D) {
				Figura2D figura2 = (Figura2D)figuras;
				System.out.println(figura2.calcArea());
			}
			
			if(figuras instanceof Figura3D) {
				Figura3D figura3 = (Figura3D)figuras;
				System.out.println(figura3.calcArea());
				System.out.println(figura3.calcVolume());
			}	
		}
  }
	}
