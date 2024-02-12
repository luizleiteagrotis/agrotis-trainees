package exercicios.aula36a43;

import java.util.Calendar;

public class ContaPoupanca extends ContaBancaria{
	
	private int diaRendimento;

	public int getDiaRendimento() {
		return diaRendimento;
	}

	public void setDiaRendimento(int diaRendimento) {
		this.diaRendimento = diaRendimento;
	}
	
	public void calcularNovoSaldo(double taxaRendimento) {
		Calendar hoje = Calendar.getInstance();
		if(diaRendimento == hoje.get(Calendar.DAY_OF_MONTH)) {
			saldo += saldo*taxaRendimento;
		}
	}

	@Override
	public String toString() {
		return "ContaPoupanca [diaRendimento = " + diaRendimento + ", nomeCliente = " + nomeCliente + ", numConta = "
				+ numConta + ", saldo = R$ " + String.format("%.2f", saldo) + "]";
	}

}
