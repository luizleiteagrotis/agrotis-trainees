package exercicios.aula36a43.exercicio1;

public abstract class ContaBancaria {

	private String nomeCliente;
	private Long numConta;
	private Double saldo = 0.0;

	public ContaBancaria() {
	}

	public ContaBancaria(String nomeCliente, Long numConta, Double saldo) {
		this.nomeCliente = nomeCliente;
		this.numConta = numConta;
		this.saldo = saldo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Long getNumConta() {
		return numConta;
	}

	public void setNumConta(Long numConta) {
		this.numConta = numConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	private boolean saqueValido(double valorDoSaque) {
		if (valorDoSaque > saldo) {
			System.out.println("Saldo insuficiente: ");
			return false;
		} else {
			System.out.println("Fazendo saque no valor " + valorDoSaque);
			return true;

		}
	}

	public void realizarSaque(double valorDoSaque) {
		if (saqueValido(valorDoSaque)) {
			this.saldo -= valorDoSaque;
			consultaDeSaldo();
		}
	}

	public void deposito(double valorDoDeposito) {
		if (valorDoDeposito > 0) {
			this.saldo += valorDoDeposito;
			consultaDeSaldo();
		}
	}

	public void consultaDeSaldo() {
		System.out.println("Saldo atual: " + this.saldo);
	}
	

	@Override
	public String toString() {
		return "ContaBancaria: Cliente: " + nomeCliente 
				+ ", Numero da Conta: " + numConta 
				+ ", Saldo: " + saldo + "";
	}

}
