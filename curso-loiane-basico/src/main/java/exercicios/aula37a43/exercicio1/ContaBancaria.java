package exercicios.aula37a43.exercicio1;

public class ContaBancaria {

	private String nomeClinete;
	private String numConta;
	private double saldo;

	public ContaBancaria() {
		super();
	}

	public ContaBancaria(String nomeClinete, String numConta, double saldo) {
		super();
		this.nomeClinete = nomeClinete;
		this.numConta = numConta;
		this.saldo = saldo;
	}

	public String getNomeClinete() {
		return nomeClinete;
	}

	public void setNomeClinete(String nomeClinete) {
		this.nomeClinete = nomeClinete;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContaBancaria [nomeClinete=");
		builder.append(nomeClinete);
		builder.append(", numConta=");
		builder.append(numConta);
		builder.append(", saldo=");
		builder.append(saldo);
		builder.append("]");
		return builder.toString();
	}

	public void sacar(double valorSaque) {
		if (saldo >= valorSaque) {
			saldo -= valorSaque;
			System.out.println("Saldo atual: " + saldo);
		} else {
			System.out.println("Saldo insuficiente: " + saldo);
		}
	}

	public void depositar(double valorDeposito) {
		saldo += valorDeposito;
		System.out.println("Saldo atual: " + saldo);
	}

}
