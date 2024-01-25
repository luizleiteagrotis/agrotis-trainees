package exercicios.aula25a27.exercicio2;

import java.util.Objects;

public class ContaCorrente {

	private Long numeroDaConta;
	private Double saldo;
	private Double limite;
	private Double limiteChequeEspecial;
	private boolean chequeEspecial;

	public ContaCorrente() {
	}

	public ContaCorrente(Long numeroDaConta, Double saldo, Double limite) {
		this.numeroDaConta = numeroDaConta;
		this.saldo = saldo;
		this.limite = limite;
		this.chequeEspecial = false;
	}

	public Long getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(Long numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public boolean isContaEspecial() {
		return chequeEspecial;
	}

	public void setContaEspecial(boolean contaEspecial) {
		this.chequeEspecial = contaEspecial;
	}

	public double consultaSaldo() {
		System.out.println("Saldo disponível: " + this.saldo);
		return this.saldo;
	}

	public void deposita(double valor) {
		this.saldo = valor;
		System.out.println("Fazendo depósito de: " + valor + " na conta numero: " + this.numeroDaConta);
	}

	public void saque(double valorDoSaque) {
		if (validaSaque(valorDoSaque)) {
			this.saldo -= valorDoSaque;
			System.out.println("Fazendo saque de: " + valorDoSaque + " na conta " + this.numeroDaConta);
		}
	}

	public boolean validaSaque(double valorDoSaque) {
		if (valorDoSaque > this.saldo || valorDoSaque > this.limite || valorDoSaque > this.limiteChequeEspecial) {
			System.out.println("Impossivel realizar saque, sem saldo/limite.");
			return false;
		}

		return true;
	}

	public boolean ehClienteEspecial() {
		if (chequeEspecial) {
			System.out.println("Cliente possui cheque especial");
			return false;
		}
		System.out.println("Cliente não possui cheque especial");
		return true;
	}

	public Double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(Double limiteChequeEspecial) {
		if (limiteChequeEspecial > 0) {
			System.out.println("Limite atual: "+ limiteChequeEspecial);
			this.limiteChequeEspecial = limiteChequeEspecial;
			this.chequeEspecial = true;
		} else {
			System.out.println("Limite atual: "+ limiteChequeEspecial);
			this.limiteChequeEspecial = limiteChequeEspecial;
			this.chequeEspecial = false;
		}

	}

	public boolean isChequeEspecial() {
		return chequeEspecial;
	}

	public void setChequeEspecial(boolean chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}

	@Override
	public String toString() {
		return "ContaCorrente [numeroDaConta=" + numeroDaConta + ", saldo=" + saldo + ", limite=" + limite
				+ ", contaEspecial=" + chequeEspecial + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroDaConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrente other = (ContaCorrente) obj;
		return Objects.equals(numeroDaConta, other.numeroDaConta);
	}

}
