package exercicios.aula25a27.exercicio2;

public class ContaCorrente {
	
	private long numero;
	private double saldo;
	private double limite;
	
	public ContaCorrente(long numero, double saldo, double limite) {
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
	}
	
	public double sacar(double valor) {
		double saldoPrevisto = saldo - valor;
		if (saldoPrevisto >= -limite) { 
			saldo = saldoPrevisto;
			return saldo;
		}
		return 0;
	}
	
	public void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
		}
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public boolean ehEspecial() {
		return saldo < 0;
	}
	
	@Override
	public String toString() {
		return "numero: " + 1 
				+ " saldo: " + saldo
				+ " limite: " + limite
				+ " especial: " + ehEspecial();
	}
}
