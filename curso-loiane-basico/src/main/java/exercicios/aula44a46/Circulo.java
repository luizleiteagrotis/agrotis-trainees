package exercicios.aula44a46;

public class Circulo extends Figura2D{
	
	
	private double raioCirculo;

	public double getRaioCirculo() {
		return raioCirculo;
	}

	public void setRaioCirculo(double raioCirculo) {
		this.raioCirculo = raioCirculo;
	}

	@Override
	public double calcArea() {
		
		return raioCirculo * raioCirculo * 3.14;
	}
	
	
	

}
