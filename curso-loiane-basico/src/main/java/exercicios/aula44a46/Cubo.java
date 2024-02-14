package exercicios.aula44a46;

public class Cubo extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica{
	
	private double lado;
	
	public Cubo(String nome, String cor, double lado) {
		super(nome, cor);
		this.lado = lado;
	}


	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	@Override
	public double calcularArea() {
		return lado*lado*6;
	}

	@Override
	public double calcularVolume() {
		return lado*lado*lado;
	}
	
}
