package exercicios.aula44a46.exercicio1.figuras3D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1.Figura3D;

public class Cilindro extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {
	private Double raio;
	private Double altura;
	private Double volume;

	public Cilindro(Double raio, Double altura) {
		this.raio = raio;
		this.altura = altura;
	}

	@Override
	public void calcularArea() {
		double areaLateral = 2 * Math.PI * raio * altura;
		double areaBases = 2 * Math.PI * Math.pow(raio, 2);
		double areaSuperficial = areaLateral + areaBases;
		System.out.println("Área Superficial do Cilindro: " + areaSuperficial);
	}

	@Override
	public void calcularVolume() {
		this.volume = (Math.PI * (raio * raio) * altura);

	}

	@Override
	public String toString() {
		return "Cilindro [raio=" + raio + ", altura=" + altura + ", volume=" + volume + "]";
	}
	
	

}
