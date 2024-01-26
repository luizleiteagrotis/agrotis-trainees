package exercicios.aula36a43;

public abstract class Contribuinte {
	
	
	private String nome;
	private double renda;
	
	
	@Override
	public String toString() {
		String str = "Nome do Contribuinte: " + nome;
		str += "Renda: " + renda;
		return str;
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getRenda() {
		return renda;
	}
	public void setRenda(double renda) {
		this.renda = renda;
	}
	
	public abstract double calculoImpost();
	
	
	
	
}
