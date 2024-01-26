package exercicios.aula36a43.exercicio3;

public class Urso extends Mamifero {
	private String alimentoPreferido;

	public Urso() {
		this.setCor("Castanho");
		this.alimentoPreferido = "MEL";
	}

	public Urso(String nome, int comprimento, String cor, double velocidade) {
		this.setNome(nome);
		this.setComprimento(comprimento);
		this.setCor(cor);
		this.setVelocidade(velocidade);
		this.alimentoPreferido = "MEL";
	}

	@Override
	public String toString() {
		return "Urso Alimento Preferido=" + alimentoPreferido 
				+ ", Nome: " + getNome() 
				+ ", Comprimento: " + getComprimento() 
				+ ", NumeroDePatas: " + getNumeroDePatas() 
				+ ", Cor: " + getCor() 
				+ ", Ambiente: " + getAmbiente() 
				+ ", Velocidade: " + getVelocidade() + "]";
	}

}
