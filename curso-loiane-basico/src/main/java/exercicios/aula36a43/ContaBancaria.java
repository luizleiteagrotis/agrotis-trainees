package exercicios.aula36a43;

public class ContaBancaria {

	protected String nomeCliente;
	protected int numConta;
	protected double saldo;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public int getNumConta() {
		return numConta;
	}
	public void setNumConta(int numConta) {
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
		return "ContaBancaria [nomeCliente = " + nomeCliente + ", numConta = " + numConta + ", saldo = R$ " + String.format("%.2f", saldo) + "]";
	}
	
	public boolean sacar(double valorSaque) {
		if(saldo > valorSaque) {
			saldo -= valorSaque;
			return true;
		}
		return false;
	}
	
	public void depositar(double valorDeposito) {
		saldo += valorDeposito;
	}
	
}
