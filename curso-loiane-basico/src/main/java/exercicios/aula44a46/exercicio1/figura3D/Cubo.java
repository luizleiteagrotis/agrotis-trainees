package exercicios.aula44a46.exercicio1.figura3D;

import exercicios.aula44a46.exercicio1.interfaces.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.interfaces.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1.interfaces.Figura3D;

public class Cubo extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {
    private double aresta;
    
    

    public double getAresta() {
		return aresta;
	}

	public void setAresta(double aresta) {
		this.aresta = aresta;
	}

	@Override
    public double calcularArea() {
        return 6 * Math.pow(aresta, 2);

    }

    @Override
    public double calcularVolume() {
        return Math.pow(aresta, 3);

    }

}
