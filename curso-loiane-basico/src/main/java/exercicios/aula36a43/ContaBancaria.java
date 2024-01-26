package exercicios.aula36a43;

public class ContaBancaria {
	
	private String nomeCliente;
	private String numeroConta;
	private double saldo;
	
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		String str = " Dados da Conta : \n";
		
		str += " Cliente Conta Bancaria: \n" + nomeCliente;
		str += " Numero da Conta: \n" + numeroConta;
		str += " Saldo da conta: \n" + saldo;
		
		return str;
	}
	
	public void depositar(double valor) {
		 saldo += valor;
	}
	
	public boolean saque(double valor) {
		if ((saldo - valor) >=0) {
			 saldo -= valor;
			 return true;
		}
		return false;
	}
	
	
	

}
