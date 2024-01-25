package exercicios.aula36a43.exercicio3;

public class Mamifero extends Animal {

	public Mamifero() {
		this.setAmbiente("Terra");
		this.setNumeroDePatas(4);

	}

	public Mamifero(String nome, int comprimento, String cor, double velocidade) {
		this.setNome(nome);
		this.setComprimento(comprimento);
		this.setCor(cor);
		this.setVelocidade(velocidade);
		this.setAmbiente("Terra");
		this.setNumeroDePatas(4);

	}

	@Override
	public String toString() {
		return "Mamifero [getNome()=" + getNome() + ", getComprimento()=" + getComprimento() + ", getNumeroDePatas()="
				+ getNumeroDePatas() + ", getCor()=" + getCor() + ", getAmbiente()=" + getAmbiente()
				+ ", getVelocidade()=" + getVelocidade() + "]";
	}

}
