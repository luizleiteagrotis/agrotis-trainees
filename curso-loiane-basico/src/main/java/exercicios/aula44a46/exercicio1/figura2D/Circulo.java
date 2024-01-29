package exercicios.aula44a46.exercicio1.figura2D;

import exercicios.aula44a46.exercicio1.interfaces.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.interfaces.Figura2D;

public class Circulo extends Figura2D implements DimensaoSuperficial {
    private double raio;
    

    public double getRaio() {
		return raio;
	}


	public void setRaio(double raio) {
		this.raio = raio;
	}


	@Override
    public double calcularArea() {
        return Math.PI * (raio * raio);
    }

}
