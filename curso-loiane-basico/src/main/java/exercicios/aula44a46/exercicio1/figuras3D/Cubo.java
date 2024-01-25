package exercicios.aula44a46.exercicio1.figuras3D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1.Figura3D;

public class Cubo extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {
	private Double lado;
	private Double areaSuperficial;
	private Double volume;

	public Cubo() {
	}

	public Cubo(Double lado) {
		this.lado = lado;
	}

	@Override
	public void calcularArea() {
		areaSuperficial = 6 * Math.pow(lado, 2);
		System.out.println("Área Superficial do Cubo: " + areaSuperficial);
	}

	@Override
	public void calcularVolume() {
		volume = Math.pow(lado, 3);
		System.out.println("Volume do Cubo: " + volume);
	}

}
