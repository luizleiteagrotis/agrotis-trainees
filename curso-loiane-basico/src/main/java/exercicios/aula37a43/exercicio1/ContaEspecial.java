package exercicios.aula37a43.exercicio1;

public class ContaEspecial extends ContaBancaria {

	private double limite;

	public ContaEspecial() {
		super();
	}

	public ContaEspecial(double limite) {
		super();
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContaEspecial [limite=");
		builder.append(limite);
		builder.append(" " + super.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public void sacar(double valorSaque) {
		double saldoComLimite = limite + super.getSaldo();
		if ( saldoComLimite >= valorSaque) {
			super.setSaldo(super.getSaldo() - valorSaque);
			System.out.println("Saldo atual: " + super.getSaldo());
		} else {
			System.out.println("Saldo e limite não suficiente" + super.getSaldo());
		}
	}

}
