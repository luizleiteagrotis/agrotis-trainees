package exercicios.aula25a27.exercicio1;

public class Lampada {
	
	private boolean ligada;
	
	 public void ligarLampada() {
		 ligada = true;
	 }
	 
	 public void desligarLampada() {
		 ligada = false;
	 }

	public boolean isLigada() {
		return ligada;
	}

	public void setLigada(boolean isLigada) {
		this.ligada = isLigada;
	}
	 
	 

}
