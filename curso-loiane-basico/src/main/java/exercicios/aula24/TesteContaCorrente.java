package exercicios.aula24;

public class TesteContaCorrente {
	
		public static void main(String[] args) {
			

	        ContaCorrente conta = new ContaCorrente ();
			
	        conta.numero = 212425;
			conta.agencia = 8081;
			conta.limiteChequeEspecial = 880;
			conta.saldo = 13500;
			conta.cartaoCreditoVinculadoAConta = "Itau Visa Click ";
			conta.limiteCartaoCredito = 11500;
			conta.especial = true;
		
			System.out.println("Numero da conta: " + conta.numero);
			System.out.println("Agencia da conta: " + conta.agencia);
			System.out.println("Limite do cheque especial da conta: " + conta.limiteChequeEspecial);
			System.out.println("A conta de numero: " + conta.numero + " tem o saldo de: " + conta.saldo + " status da conta: " + conta.especial);
			System.out.println("Nome do Cartao de credito vinculado a conta: " + conta.cartaoCreditoVinculadoAConta);
			System.out.println("Limite do Cartao de Credito: " + conta.limiteCartaoCredito);

		}
}
	


