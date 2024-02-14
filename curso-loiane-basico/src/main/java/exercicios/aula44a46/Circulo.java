package exercicios.aula44a46;

public class Circulo extends Figura2D implements DimensaoSuperficial{

	private double raio;
	
	public Circulo(String nome, String cor, double raio) {
		super(nome, cor);
		this.raio = raio;
	}
	
	public double getRaio() {
		return raio;
	}
	public void setRaio(double raio) {
		this.raio = raio;
	}
	
	@Override
	public double calcularArea() {
		return raio*raio*Math.PI;
	}
	
	public double calcCircunferencia() {
		return raio*2*Math.PI;
	}

}
