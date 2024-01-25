package exercicios.aula36a43.exercicio1;

public class ContaBancariaTest {
	public static void main(String[] args) {

		ContaPoupanca contaPoupanca = new ContaPoupanca();
		contaPoupanca.setNomeCliente("Anderson");
		contaPoupanca.deposito(100);
		contaPoupanca.calcularNovoSaldo(0.05);
		System.out.println(contaPoupanca);

		ContaEspecial contaEspecial = new ContaEspecial();
		contaEspecial.setNomeCliente("Gustavo");
		contaEspecial.deposito(1500);
		contaEspecial.realizarSaque(0);
		System.out.println(contaEspecial);

	}

}
