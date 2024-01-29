package exercicios.aula44a46;

public class Quadrado extends Figura2D {
		
	private double lado;

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}
	
	@Override
	public double calcularArea() {
		return lado * lado;
	}
	
	

}
