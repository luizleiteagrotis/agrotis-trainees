package exercicios.aula44a46.exercicio1.figuras3D;

import exercicios.aula44a46.exercicio1.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1.Figura3D;

public class Piramide extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {
	private double baseLargura;
	private double baseComprimento;
	private double altura;

	public Piramide(double baseLargura, double baseComprimento, double altura) {
		this.baseLargura = baseLargura;
		this.baseComprimento = baseComprimento;
		this.altura = altura;
	}

	@Override
	public void calcularArea() {
		double areaBase = baseLargura * baseComprimento;
		double areaLateral = calcularAreaLateral();
		double areaTotal = areaBase + areaLateral;

		System.out.println("Área da Pirâmide: " + areaTotal);
	}

	@Override
	public void calcularVolume() {
		double volume = (baseLargura * baseComprimento * altura) / 3.0;

		System.out.println("Volume da Pirâmide: " + volume);
	}

	private double calcularAreaLateral() {
		double semiPerimetro = (baseLargura + baseComprimento + calcularHipotenusa()) / 2.0;
		double areaLateral = baseLargura * altura / 2.0 + baseComprimento * altura / 2.0 + semiPerimetro * altura;

		return areaLateral;
	}

	private double calcularHipotenusa() {
		return Math.sqrt(baseLargura * baseLargura + baseComprimento * baseComprimento);
	}
}
