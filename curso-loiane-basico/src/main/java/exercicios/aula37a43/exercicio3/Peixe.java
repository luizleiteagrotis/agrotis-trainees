package exercicios.aula37a43.exercicio3;

public class Peixe extends Animal {

	private String caracteristicas;

	public Peixe() {
		super();
		super.setNumPatas(0);
		super.setAmbiente("Mar");
		super.setCor("Cinzento");
	}

	public Peixe(String caracteristicas) {
		super();
		super.setNumPatas(0);
		super.setAmbiente("Mar");
		super.setCor("Cinzento");
		this.caracteristicas = caracteristicas;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += "\nCaracterísticas: " + caracteristicas;
		return s;
	}

}
