package exercicios.aula25a27.exercicio2;

public class ContaCorrenteTest {

	public static void main(String[] args) {

		ContaCorrente contaUm = new ContaCorrente();

		contaUm.setNumeroDaConta(123456789L);
		contaUm.setLimite(100.00);
		contaUm.setLimiteChequeEspecial(100.00);
		contaUm.deposita(100.00);
		contaUm.consultaSaldo();
		contaUm.saque(80);
		contaUm.ehClienteEspecial();
		contaUm.consultaSaldo();
		contaUm.setLimiteChequeEspecial(0.0);
		contaUm.ehClienteEspecial();

	}

}
