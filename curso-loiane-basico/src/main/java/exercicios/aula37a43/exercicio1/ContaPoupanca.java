package exercicios.aula37a43.exercicio1;

public class ContaPoupanca extends ContaBancaria{
	
	private double taxaRendimentoDia;
	
	public ContaPoupanca(String nomeCliente) {
		super(nomeCliente);
		taxaRendimentoDia = 0.10;
	}

	public void calcularNovoSaldo() {
		saldo *= taxaRendimentoDia + 1;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tTaxaRendimentoDia: " + taxaRendimentoDia;
	}
}
