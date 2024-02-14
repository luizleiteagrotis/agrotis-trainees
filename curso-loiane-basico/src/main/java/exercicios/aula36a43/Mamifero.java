package exercicios.aula36a43;

public class Mamifero extends Animal{

	private String alimento;

	public Mamifero(String nome, String cor, String ambiente, double comprimento, double velocidade, int numPatas, String alimento) {
		super(nome, cor, ambiente, comprimento, velocidade, numPatas);
		this.alimento = alimento;
	}

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}
	
	@Override
	public String toString() {
		return "Animal: " + this.nome + "\nComprimento: " + this.comprimento + " cm\nPatas: " + this.numPatas + "\nCor: " + this.cor +
				"\nAmbiente: " + this.ambiente + "\nVelocidade: " + this.velocidade + " m/s\nAlimento: " + this.alimento;
				
	}

}
