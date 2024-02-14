package exercicios.aula44a46;

public class Piramide extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica{
	
	private double base;
	private double altura;
	
	public Piramide(String nome, String cor, double base, double altura) {
		super(nome, cor);
		this.base = base;
		this.altura = altura;
	}
	
	public double getBase() {
		return base;
	}
	
	public void setBase(double base) {
		this.base = base;
	}
	
	public double getAltura() {
		return altura;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	@Override
	public double calcularArea() {
		double apotema = Math.sqrt((base/2)*(base/2) + altura*altura);
		return base*base + 2*apotema*base;
	}
	@Override
	public double calcularVolume() {
		return base*base*altura/3;
	}
	
}
