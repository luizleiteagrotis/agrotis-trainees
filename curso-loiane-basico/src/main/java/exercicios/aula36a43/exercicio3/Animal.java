package exercicios.aula36a43.exercicio3;

public abstract class Animal {
	private String nome;
	private int comprimento;
	private Integer numeroDePatas;
	private String cor;
	private Ambiente ambiente;
	private Double velocidade;

	public Animal() {
	}

	public Animal(String nome, int comprimento, Integer numeroDePatas, String cor, Ambiente ambiente,
			Double velocidade) {
		this.nome = nome;
		this.comprimento = comprimento;
		this.numeroDePatas = numeroDePatas;
		this.cor = cor;
		this.ambiente = ambiente;
		this.velocidade = velocidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getComprimento() {
		return comprimento;
	}

	public void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}

	public Integer getNumeroDePatas() {
		return numeroDePatas;
	}

	public void setNumeroDePatas(Integer numeroDePatas) {
		this.numeroDePatas = numeroDePatas;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}

}
