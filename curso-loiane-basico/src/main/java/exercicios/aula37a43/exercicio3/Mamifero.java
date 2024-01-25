package exercicios.aula37a43.exercicio3;

public class Mamifero extends Animal {

	private String alimentoFavorito;

	public Mamifero() {
		super();
		super.setAmbiente("Terra");
	}

	public Mamifero(String alimentoFavorito) {
		super();
		super.setAmbiente("Terra");
		this.alimentoFavorito = alimentoFavorito;
	}

	public String getAlimentoFavorito() {
		return alimentoFavorito;
	}

	public void setAlimentoFavorito(String alimentoFavorito) {
		this.alimentoFavorito = alimentoFavorito;
	}

	@Override
	public String toString() {
		String s = super.toString();
		s += "\nAlimento favorito: " + alimentoFavorito;
		return s;
	}


	
	
}
