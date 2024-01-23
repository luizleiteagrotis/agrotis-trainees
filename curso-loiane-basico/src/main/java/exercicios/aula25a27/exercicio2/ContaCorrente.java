package exercicios.aula25a27.exercicio2;

public class ContaCorrente {

	private String numero;
	private double saldo;
	private boolean especial;
	private double limiteEspecial;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isEspecial() {
		return especial;
	}

	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

	public double getLimiteEspecial() {
		return limiteEspecial;
	}

	public void setLimiteEspecial(double limiteEspecial) {
		this.limiteEspecial = limiteEspecial;
	}

	public void consultarSaldo() {
		System.out.println(saldo);
	}

	public void realizarSaque(double valorSaque) {
		if (saldo >= valorSaque) {
			saldo -= valorSaque;
			consultarSaldo();
		} else {
			if (especial) {
				double limite = limiteEspecial + saldo;
				if (limite >= valorSaque) {
					saldo -= valorSaque;
					consultarSaldo();
				}
			} else {
				System.out.println("Não há saldo suficiente");
				consultarSaldo();
			}
		}
	}

	public void depositarDinheiro(double valorDeposito) {
		saldo += valorDeposito;
		consultarSaldo();
	}

	public boolean chequeEspecial() {
		return saldo < 0;
	}

}
