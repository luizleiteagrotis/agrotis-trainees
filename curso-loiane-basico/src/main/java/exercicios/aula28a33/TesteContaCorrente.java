package exercicios.aula28a33;

public class TesteContaCorrente {

	public static void main(String[] args) {

		ContaCorrente conta = new ContaCorrente();

		conta.setNumero(212425);
		conta.setAgencia (8081);
		conta.setLimiteChequeEspecial (500);
		conta.setSaldo (-150);
		conta.setEspecial(true);

		System.out.println("Numero da conta: " + conta.getNumero());
		System.out.println("Agencia da conta: " + conta.getAgencia());
		System.out.println("Limite do cheque especial da conta: " + conta.getLimiteChequeEspecial());
		System.out.println("A conta de numero: " + conta.getNumero() + " tem o saldo de: " + conta.getSaldo()
				+ " status da conta: " + conta.isEspecial());
		

		boolean saqueConcluido = conta.efetuarSaque(200);

		if (saqueConcluido) {
			System.out.println(
					"Saque Concluido com sucesso! " + " O saldo da conta atualizado e de: " + conta.getSaldo());
		} else {
			System.out.println("Nao ha saldo para saque!");
		}

		conta.realizarDeposito(500);
		System.out.println("Saldo atual da conta: " + conta.getSaldo());

		if (conta.consultaChequeEspecial()) {
			System.out.println("Cheque especial em uso!");
		} else {
			System.out.println("Cheque especial nao esta sendo utilizado.");
		}
	}
}
