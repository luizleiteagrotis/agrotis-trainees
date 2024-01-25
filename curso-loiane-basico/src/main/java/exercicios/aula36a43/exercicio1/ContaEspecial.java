package exercicios.aula36a43.exercicio1;

public class ContaEspecial extends ContaBancaria {

	private Double limite;

	public ContaEspecial() {
		super();
		System.out.println("Criando conta especial.");
	}

	public ContaEspecial(double limite) {
		super();
		this.limite = limite;
	}

	@Override
	public void realizarSaque(double valorDoSaque) {
		double saldoAtual = getSaldo();
		double novoSaldo = saldoAtual + valorDoSaque;
	}

}
