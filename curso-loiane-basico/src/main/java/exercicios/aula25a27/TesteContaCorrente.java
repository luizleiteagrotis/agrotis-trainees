package exercicios.aula25a27;

import exercicios.aula25a27.ContaCorrente;

public class TesteContaCorrente {

	public static void main(String[] args) {
		
		    ContaCorrente conta = new ContaCorrente ();
			
	        conta.numero = 212425;
			conta.agencia = 8081;
			conta.limiteChequeEspecial = 500;
			conta.saldo = -150;
			conta.cartaoCreditoVinculadoAConta = "Itau Visa Click ";
			
			conta.especial = true;
		
			System.out.println("Numero da conta: " + conta.numero);
			System.out.println("Agencia da conta: " + conta.agencia);
			System.out.println("Limite do cheque especial da conta: " + conta.limiteChequeEspecial);
			System.out.println("A conta de numero: " + conta.numero + " tem o saldo de: " + conta.saldo + " status da conta: " + conta.especial);
			System.out.println("Nome do Cartao de credito vinculado a conta: " + conta.cartaoCreditoVinculadoAConta);
			
			
		    boolean saqueConcluido = conta.efetuarSaque(200);
			
			if(saqueConcluido) {
				System.out.println("Saque Concluido com sucesso! " + " O saldo da conta atualizado e de: " + conta.saldo);
			} else {
				System.out.println("Nao ha saldo para saque!");
			}
		
			conta.realizarDeposito(500);
			System.out.println("Saldo atual da conta: " + conta.saldo);
			
			if(conta.consultaChequeEspecial()){
				System.out.println("Cheque especial em uso!");
			} else {
				System.out.println("Cheque especial nao esta sendo utilizado.");
			}
	}

}
