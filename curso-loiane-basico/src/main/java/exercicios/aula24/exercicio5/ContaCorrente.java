package exercicios.aula24.exercicio5;

import java.util.Objects;

public class ContaCorrente {

	private Long numeroDaConta;
	private Double saldo;
	private Double limite;
	private Double limiteContaEspecial = null;
	private boolean contaEspecial;

	public ContaCorrente() {
	}

	public ContaCorrente(Long numeroDaConta, Double saldo, Double limite) {
		this.numeroDaConta = numeroDaConta;
		this.saldo = saldo;
		this.limite = limite;
		this.contaEspecial = false;
	}

	public ContaCorrente(Long numeroDaConta, Double saldo, Double limite, Double limiteContaEspecial,
			boolean contaEspecial) {
		this.numeroDaConta = numeroDaConta;
		this.saldo = saldo;
		this.limite = limite;
		this.limiteContaEspecial = limiteContaEspecial;
		this.contaEspecial = contaEspecial;
	}

	public Long getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(Long numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public boolean isContaEspecial() {
		return contaEspecial;
	}

	public void setContaEspecial(boolean contaEspecial) {
		this.contaEspecial = contaEspecial;
	}

	@Override
	public String toString() {
		return "ContaCorrente [numeroDaConta=" + numeroDaConta + ", saldo=" + saldo + ", limite=" + limite
				+ ", contaEspecial=" + contaEspecial + "]";
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
