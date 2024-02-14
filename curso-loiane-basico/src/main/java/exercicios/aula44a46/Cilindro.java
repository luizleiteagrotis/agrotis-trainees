package exercicios.aula44a46;

public class Cilindro extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica{

	private double raio;
	private double altura;
	
	public Cilindro(String nome, String cor, double raio, double altura) {
		super(nome, cor);
		this.raio = raio;
		this.altura = altura;
	}
	
	public double getAltura() {
		return altura;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	public double getRaio() {
		return raio;
	}
	
	public void setRaio(double raio) {
		this.raio = raio;
	}
	
	@Override
	public double calcularArea() {
		return 2*raio*raio*Math.PI + 2*Math.PI*raio*altura;
	}
	
	@Override
	public double calcularVolume() {
		return raio*raio*Math.PI*altura;
	}
	
	
}
