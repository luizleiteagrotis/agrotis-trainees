package exercicios.aula44a46.exercicio1.figuras2D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1.Figura2D;

public class Circulo extends Figura2D implements DimensaoSuperficial, DimensaoVolumetrica {

	private Double raio;
	private Double pi = Math.PI;
	private Double volume;
	private Double area;

	@Override
	public void calcularArea() {
		this.area = Math.PI * raio * raio;
	}

	@Override
	public void calcularVolume() {
		this.volume = (4 * Math.PI * (raio * raio * raio)) / 3;
	}

}
