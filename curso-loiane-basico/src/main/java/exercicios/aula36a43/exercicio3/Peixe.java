package exercicios.aula36a43.exercicio3;

public class Peixe extends Animal {

	private boolean barbatana = true;
	private boolean calda = true;

	public Peixe() {
		this.setNumeroDePatas(0);
		this.setAmbiente(Ambiente.MAR);
		this.setCor("Cinzenta");
	}

	public Peixe(String nome, int comprimento, double velocidade) {
		this.setNome(nome);
		this.setComprimento(comprimento);
		this.setVelocidade(velocidade);
		this.setNumeroDePatas(0);
		this.setAmbiente(Ambiente.MAR);
		this.setCor("Cinzenta");
	}

	@Override
	public String toString() {
		return "Peixe: Possui barbatana? " + barbatana 
				+ ", Possui calda?" + calda 
				+ ", Nome" + getNome() 
				+ ", Comprimento: " + getComprimento() 
				+ ", NumeroDePatas: " + getNumeroDePatas() 
				+ ", Cor: " + getCor()
				+ ", Ambiente: " + getAmbiente() 
				+ ", Velocidade: " + getVelocidade() + "]";
	}

}
