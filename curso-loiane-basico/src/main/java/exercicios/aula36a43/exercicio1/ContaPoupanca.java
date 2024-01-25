package exercicios.aula36a43.exercicio1;

public class ContaPoupanca extends ContaBancaria {

	private int diaRendimento;

	public ContaPoupanca() {
		System.out.println("Criando conta poupança.");
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

	public void calcularNovoSaldo(double taxaDeRendimento) {
		double saldoAtual = getSaldo();
		double novoSaldo = saldoAtual * (1 + taxaDeRendimento / 100.0);
		System.out.println("Saldo com correção de rendimentos: " + saldoAtual);
		setSaldo(novoSaldo);
	}

}
