package exercicios.aula37a43.exercicio1;

import java.util.Calendar;

public class ContaPoupanca extends ContaBancaria {

	private int diaRendimento;

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(int diaRendimento) {
		super();
		this.diaRendimento = diaRendimento;
	}

	public int getDiaRendimento() {
		return diaRendimento;
	}

	public void setDiaRendimento(int diaRendimento) {
		this.diaRendimento = diaRendimento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContaPoupanca [diaRendimento=");
		builder.append(diaRendimento);
		builder.append(" " + super.toString());
		builder.append("]");
		return builder.toString();
	}

	public void calcularNovoSaldo(double taxaRendimento) {

		Calendar hoje = Calendar.getInstance();

		if (diaRendimento == hoje.get(Calendar.DAY_OF_MONTH)) {
			super.setSaldo(super.getSaldo() + (super.getSaldo() * taxaRendimento));
			System.out.println( String.format("%.2f", super.getSaldo()));
		} else {
			System.out.println("Não é o dia de rendimento");
		}
	}

}
