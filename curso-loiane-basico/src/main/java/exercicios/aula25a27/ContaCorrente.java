package exercicios.aula25a27;

public class ContaCorrente {
	
	public int codigo;
	public double saldo;
	public double limite;
	public boolean especial;
	
	public double sacarValor(double valor) {
		if(valor < saldo) {
			saldo = saldo - valor;
			return valor;
		}else {
			System.out.println("Valor indísponível!");
			return 0;
		}
	}
	
	public void depositarValor(double valor) {
		saldo = saldo + valor;
	}
	
	public double consultarSaldo() {
		return saldo;
	}
	
	public boolean verificaChequeEspecial() {
		return especial;
	}
	
}
