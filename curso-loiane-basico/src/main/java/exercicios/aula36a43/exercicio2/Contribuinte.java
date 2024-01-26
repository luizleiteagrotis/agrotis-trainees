package exercicios.aula36a43.exercicio2;

public class Contribuinte {
	private String nome;

	public Contribuinte(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Contribuinte: " + nome+"\n";
	}
	
	

	

}
