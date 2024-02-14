package exercicios.aula36a43;

public class Animal {

	protected String nome;
	protected String cor;
	protected String ambiente;
	protected double comprimento;
	protected double velocidade;
	protected int numPatas;
	
	public Animal(String nome, String cor, String ambiente, double comprimento, double velocidade, int numPatas) {
		this.nome = nome;
		this.cor = cor;
		this.ambiente = ambiente;
		this.comprimento = comprimento;
		this.velocidade = velocidade;
		this.numPatas = numPatas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	public int getNumPatas() {
		return numPatas;
	}

	public void setNumPatas(int numPatas) {
		this.numPatas = numPatas;
	}
	
	@Override
	public String toString() {
		return "Animal: " + this.nome + "\nComprimento: " + this.comprimento + " cm\nPatas: " + this.numPatas + "\nCor: " + this.cor +
				"\nAmbiente: " + this.ambiente + "\nVelocidade: " + this.velocidade + " m/s";
				
	}

}
