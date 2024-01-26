package exercicios.aula37a43.exercicio3;

public class Urso extends Mamifero{

	private String alimentoPrefeito;
	
	public Urso(String nome, double comprimento, double velocidade) {
		super(nome, comprimento, velocidade, Cor.CASTANHO);
		alimentoPrefeito = "Mel";
	}
	
	public Urso(String nome, double comprimento, double velocidade, Cor cor) {
		super(nome, comprimento, velocidade, cor);
		alimentoPrefeito = "Mel";
	}

	@Override
	public String toString() {
		return super.toString() + "\nAlimento preferido: " + alimentoPrefeito;
	}
}
