package exercicios.aula36a43;

public class Peixe extends Animal {
	
    private String caracteristicas;
    public Peixe() {
        super();
        this.setPatas(0);
        this.setAmbiente("Mar");
        this.setCor("Cinzenta");
        this.setCaracteristicas("Barbatanas, cauda");
    }
    public Peixe(String caracteristicas) {
        super();
        this.setPatas(0);
        this.setAmbiente("Mar");
        this.setCor("Cinzenta");
        this.caracteristicas = caracteristicas;
    }
    public Peixe(String nome, double comprimento, double velocidade) {
        super(nome, comprimento, velocidade);
        this.setPatas(0);
        this.setAmbiente("Mar");
        this.setCor("Cinzenta");
        this.setCaracteristicas("Barbatanas, cauda");
    }
    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    @Override
    public String toString() {
        return "Peixe [caracteristicas=" + caracteristicas + ", getCaracteristicas()=" + getCaracteristicas() + ", getNome()="
                        + getNome() + ", getComprimento()=" + getComprimento() + ", getPatas()=" + getPatas() + ", getCor()="
                        + getCor() + ", getAmbiente()=" + getAmbiente() + ", getVelocidade()=" + getVelocidade() + ", toString()="
                        + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }
    @Override
    public void imprimirAnimal() {
        super.imprimirAnimal();
        System.out.println("Características: " + caracteristicas);
    }
}

