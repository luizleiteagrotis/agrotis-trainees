package exercicios.aula37a43.exercicio3;

public class Peixe extends Animal{

	private String caracteristicas;
	
	public Peixe(String nome, double comprimento, double velocidade) {
		super(nome, comprimento, numeroPatas(0), Cor.CINZENTA, Ambiente.MAR, velocidade);
		caracteristicas = "Barbatanas e cauda";
	}
	
	private static int numeroPatas(int patas) {
		return patas;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nCaracteristicas: " + caracteristicas;
	}
}
