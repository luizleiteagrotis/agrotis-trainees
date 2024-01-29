package exercicios.aula44a46.exercicio1.figura3D;

import exercicios.aula44a46.exercicio1.interfaces.DimensaoSuperficial;
import exercicios.aula44a46.exercicio1.interfaces.DimensaoVolumetrica;
import exercicios.aula44a46.exercicio1.interfaces.Figura2D;
import exercicios.aula44a46.exercicio1.interfaces.Figura3D;

public class Piramide extends Figura3D implements DimensaoSuperficial, DimensaoVolumetrica {
    private double altura;
    private double arestaBase;
    private double apotema;
    private Figura2D base;
    private int numPoliBase;
    
    

    public int getNumPoliBase() {
		return numPoliBase;
	}

	public void setNumPoliBase(int numPoliBase) {
		this.numPoliBase = numPoliBase;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getArestaBase() {
		return arestaBase;
	}

	public void setArestaBase(double arestaBase) {
		this.arestaBase = arestaBase;
	}

	public double getApotema() {
		return apotema;
	}

	public void setApotema(double apotema) {
		this.apotema = apotema;
	}

	public Figura2D getBase() {
		return base;
	}

	public void setBase(Figura2D base) {
		this.base = base;
	}

	@Override
    public double calcularArea() {
		if( base == null) {
			return 0;
		}else {
			return   (numPoliBase + (arestaBase + apotema)/ 2) + base.calcularArea();
		}
	}
    @Override
    public double calcularVolume() {
    	if(base == null) {
    		return 0;
    	}else {
    		return (base.calcularArea() * altura)/ 3;
        }

    }

}
