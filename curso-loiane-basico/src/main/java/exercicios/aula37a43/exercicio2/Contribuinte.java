package exercicios.aula37a43.exercicio2;

public abstract class Contribuinte {

	private String nome;
	private double renda;

	public Contribuinte() {
		super();
	}

	public Contribuinte(String nome, double renda) {
		super();
		this.nome = nome;
		this.renda = renda;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contribuinte [nome=");
		builder.append(nome);
		builder.append(", renda=");
		builder.append(renda);
		builder.append("]");
		return builder.toString();
	}

	public abstract void calcularImposto();

}
