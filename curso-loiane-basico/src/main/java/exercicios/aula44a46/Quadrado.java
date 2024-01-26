package exercicios.aula44a46;

public class Quadrado extends Figura2D{
	
	private int ladoQuadrado;

	public int getLadoQuadrado() {
		return ladoQuadrado;
	}

	public void setLadoQuadrado(int ladoQuadrado) {
		this.ladoQuadrado = ladoQuadrado;
	}

	@Override
	public double calcArea() {
		return ladoQuadrado * ladoQuadrado;
		
	
	}
	
	
	
	
	

}
