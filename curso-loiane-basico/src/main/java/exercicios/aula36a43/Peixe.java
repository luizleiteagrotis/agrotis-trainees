package exercicios.aula36a43;

public class Peixe extends Animal{

	private String caracteristicas;
	
	public Peixe(String nome, String cor, String ambiente, double comprimento, double velocidade, int numPatas, String caracteristicas) {
		super(nome, cor, ambiente, comprimento, velocidade, numPatas);
		this.setCaracteristicas(caracteristicas);
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	@Override
	public String toString() {
		return "Animal: " + this.nome + "\nComprimento: " + this.comprimento + " cm\nPatas: " + this.numPatas + "\nCor: " + this.cor +
				"\nAmbiente: " + this.ambiente + "\nVelocidade: " + this.velocidade + " m/s\nCaracteristicas: " + this.caracteristicas;
				
	}

}
