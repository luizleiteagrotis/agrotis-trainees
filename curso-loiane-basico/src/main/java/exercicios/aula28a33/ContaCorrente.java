package exercicios.aula28a33;

public class ContaCorrente {

	private int codigo;
	private double saldo;
	private double limite;
	private boolean especial;
	
	public ContaCorrente(int codigo, double saldo, double limite, boolean especial) {
		super();
		this.codigo = codigo;
		this.saldo = saldo;
		this.limite = limite;
		this.especial = especial;
	}
	
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
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public boolean isEspecial() {
		return especial;
	}

	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

}
