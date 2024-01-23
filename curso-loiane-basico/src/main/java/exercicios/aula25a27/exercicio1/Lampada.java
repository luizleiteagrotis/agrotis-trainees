package exercicios.aula25a27.exercicio1;

public class Lampada {
	
	private boolean estaLigada;
	
	public Lampada() {
		estaLigada = false;
	}
	
	public void ligar() {
		estaLigada = true;
	}
	
	public void desligar() {
		estaLigada = false;
	}
	
	public boolean estaLigada() {
		return estaLigada;
	}
}
