package exercicios.aula37a43.exercicio3;

public abstract class Animal {
	
	private String nome;
	private double comprimento;
	private int numeroPatas;
	private Cor cor;
	private Ambiente ambiente;
	private double velocidade;
	
	public Animal(String nome, double comprimento, int numeroPatas, 
			Cor cor, Ambiente ambiente, double velocidade) {
		this.nome = nome;
		this.comprimento = comprimento;
		this.numeroPatas = numeroPatas;
		this.cor = cor;
		this.ambiente = ambiente;
		this.velocidade = velocidade;
	}

	public Animal(String nome, double comprimento, Ambiente ambiente, 
			double velocidade, Cor cor) {
		this.nome = nome;
		this.comprimento = comprimento;
		this.ambiente = ambiente;
		this.cor = cor;
		this.velocidade = velocidade;
		this.numeroPatas = 4;
	}
	
	@Override
	public String toString() {
		return "Animal: " + nome
				+ "\nComprimento: " + comprimento + " cm"
				+ "\nPatas: " + numeroPatas
				+ "\nCor: " + cor
				+ "\nAmbiente: " + ambiente
				+ "\nVelocidade: " + velocidade + " m/s";
	}
}
