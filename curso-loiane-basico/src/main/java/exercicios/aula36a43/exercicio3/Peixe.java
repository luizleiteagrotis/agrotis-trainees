package exercicios.aula36a43.exercicio3;

public class Peixe extends Animal {

	private boolean barbatana = true;
	private boolean calda = true;

	public Peixe() {
		this.setNumeroDePatas(0);
		this.setAmbiente("Mar");
		this.setCor("Cinzenta");
	}

	public Peixe(String nome, int comprimento, double velocidade) {
		this.setNome(nome);
		this.setComprimento(comprimento);
		this.setVelocidade(velocidade);
		this.setNumeroDePatas(0);
		this.setAmbiente("Mar");
		this.setCor("Cinzenta");
	}

	@Override
	public String toString() {
		return "Peixe [barbatana=" + barbatana + ", calda=" + calda + ", getNome()=" + getNome() + ", getComprimento()="
				+ getComprimento() + ", getNumeroDePatas()=" + getNumeroDePatas() + ", getCor()=" + getCor()
				+ ", getAmbiente()=" + getAmbiente() + ", getVelocidade()=" + getVelocidade() + "]";
	}


	

}
