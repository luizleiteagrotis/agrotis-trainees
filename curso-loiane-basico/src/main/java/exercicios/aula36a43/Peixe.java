package exercicios.aula36a43;

import exercicios.aula36a43.Animal;

public class Peixe extends Animal {
    
	private String caracteristicas;

    public Peixe() {
    	super();
    	super.setPatas(0);
    	super.setAmbiente("Mar");
    	super.setCor("Cinzento");
    }

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
    @Override
    public String toString() {
        return "Peixe{" +
                "caracteristicas='" + caracteristicas + '\'' +
                "} " + super.toString();
    }
}