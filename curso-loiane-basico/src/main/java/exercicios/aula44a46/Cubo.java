package exercicios.aula44a46;

public class Cubo extends Figura3D{
	
	private int ladoCubo;

	public int getLadoCubo() {
		return ladoCubo;
	}

	public void setLadoCubo(int ladoCubo) {
		this.ladoCubo = ladoCubo;
	}

	@Override
	public double calcArea() {
		
		return 6 * ladoCubo * ladoCubo;
	}

	@Override
	public double calcVolume() {
		
		return ladoCubo * ladoCubo * ladoCubo;
	}
	
	
	

}
