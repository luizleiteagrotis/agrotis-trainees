package exercicios.aula44a46;

public class Triangulo extends Figura2D{
	
	private double alturaTriangulo;
	private double baseTriangulo;
	
	
	public double getAlturaTriangulo() {
		return alturaTriangulo;
	}
	public void setAlturaTriangulo(double alturaTriangulo) {
		this.alturaTriangulo = alturaTriangulo;
	}
	public double getBaseTriangulo() {
		return baseTriangulo;
	}
	public void setBaseTriangulo(double baseTriangulo) {
		this.baseTriangulo = baseTriangulo;
	}
	@Override
	public double calcArea() {
		return alturaTriangulo * baseTriangulo / 2;
	}
	
	
	
	

}
