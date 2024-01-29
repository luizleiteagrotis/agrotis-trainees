package exercicios.aula44a46.exercicio1.figura2D;

import exercicios.aula44a46.exercicio1.interfaces.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.interfaces.Figura2D;

public class Quadrado extends Figura2D implements DimensaoSuperficial {
    private int lado;

    public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	@Override
    public double calcularArea() {
        return Math.pow(lado, 2);

    }

}
