package exercicios.aula37a43.exercicio1;

public class ContaBancaria {
	
	private static long counter = 0;
	
	private long numeroConta;
	private String nomeCliente;
	protected double saldo;
	
	public ContaBancaria(String nomeCliente) {
		setNumeroConta();
		this.nomeCliente = nomeCliente;
		saldo = 0;
	}
	
	private void setNumeroConta() {
		numeroConta = counter;
		counter++;
	}
	
	public void sacar(double valor) throws ContaBancariaException{
		if (valor > saldo) {
			throw new ContaBancariaException("Saldo insuficiente!");
		}
		saldo -= valor;
	}
	
	public void depositar(double valor) {
		saldo += valor;
	}
	
	@Override
	public String toString() {
		return "Numero: " + numeroConta + "\tNomeCliente: " + nomeCliente + "\tSaldo: " + saldo;
	}
}
