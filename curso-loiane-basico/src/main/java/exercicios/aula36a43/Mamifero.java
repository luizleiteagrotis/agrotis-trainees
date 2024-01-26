package exercicios.aula36a43;

public class Mamifero extends Animal{
	
	private String alimento;

	public Mamifero() {
		this.setAlimento("Mel");
	}

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = "mel";
	}

	@Override
	public String toString() {
		return "Mamifero [alimento=" + alimento + ", toString()=" + super.toString() + "mel";
	}
	
	
	
	
	
}
	
	
	
	
