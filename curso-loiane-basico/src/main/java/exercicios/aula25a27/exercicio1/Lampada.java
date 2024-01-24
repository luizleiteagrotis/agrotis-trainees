package exercicios.aula25a27.exercicio1;

public class Lampada {

	private boolean lampadaLigada = false;

	public Lampada() {
	}

	public Lampada(boolean lampadaLigada) {
		this.lampadaLigada = lampadaLigada;
	}

	public boolean isLampadaLigada() {
		return lampadaLigada;
	}

	public void setLampadaLigada(boolean lampadaLigada) {
		this.lampadaLigada = lampadaLigada;
	}

	public void acenderLampada() {
		if (!lampadaLigada) {
			this.lampadaLigada = true;
			System.out.println("Ligando lampada.");
		} else {
			this.lampadaLigada = false;
			System.out.println("Desligando lampada.");
		}

	}

	@Override
	public String toString() {
		return "Lampada [lampadaLigada=" + lampadaLigada + "]";
	}

}
