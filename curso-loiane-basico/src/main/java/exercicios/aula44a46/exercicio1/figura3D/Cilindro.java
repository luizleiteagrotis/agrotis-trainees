package exercicios.aula44a46.exercicio1.figura3D;

import exercicios.aula44a46.exercicio1.interfaces.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.interfaces.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1.interfaces.Figura3D;

public class Cilindro extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {
    private double raio;
    private double altura;

 

    public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
    public double calcularArea() {
        double areaBase = Math.PI * Math.pow(raio, 2);
        double areaLateral = 2 * Math.PI * raio * altura;
        return (2 * areaBase) + areaLateral;
    }

    @Override
    public double calcularVolume() {
        return Math.PI * Math.pow(raio,2) * altura;

    }

}
