package exercicios.aula36a43;

public class TesteContaBancaria {

	public static void main(String[] args) {
		
		
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		contaPoupanca.setNomeCliente("Willian rafael");
		contaPoupanca.setNumeroConta("2428-8");
		contaPoupanca.setRendimento(2);
		
		contaPoupanca.depositar(1000);
		efetuarSaque(contaPoupanca, 500);
		efetuarSaque(contaPoupanca,500);
		
		
		ContaBancaria conta = new ContaBancaria();
		conta.setNomeCliente("Willian rafael");
		conta.setNumeroConta("2428-8");
		
		conta.depositar(20000);
		efetuarSaque(conta, 13000);
		efetuarSaque(conta, 1000);
		
		ContaEspecial contaEspecial = new ContaEspecial();
		contaEspecial.setNomeCliente("Willian rafael");
		contaEspecial.setNumeroConta("2428-8");
		contaEspecial.setLimite(150);
		
		contaEspecial.depositar(2155);
		efetuarSaque(contaEspecial, 880);
		efetuarSaque(contaEspecial,1500);
		efetuarSaque(contaEspecial,1500);
		
		if (contaPoupanca.calculoNovoSaldo(0.5)) {
			System.out.println("Seu saldo com rendimento: " + contaPoupanca.getSaldo());
		} else {
			System.out.println("Sem rendimento " + contaPoupanca.getSaldo());
		}
		
		System.out.println(contaPoupanca);
		
		
	}
	
	private static void efetuarSaque(ContaPoupanca conta, double valor) {
		if(conta.saque(valor)) {
			System.out.println("O saque foi concluido. Seu saldo atual corresponde a: " + conta.getSaldo());
		}else {
			System.out.println("Voce nao possui saldo para este saque. Seu saldo atual corresponde a: " + conta.getSaldo());
		}
	}
	
	private static void efetuarSaque(ContaBancaria conta, double valor) {
		if(conta.saque(valor)) {
			System.out.println("O saque foi concluido. Seu saldo atual corresponde a: " + conta.getSaldo());
		}else {
			System.out.println("Voce nao possui saldo para este saque. Seu saldo atual corresponde a: " + conta.getSaldo());
		}
	}
	
	private static void efetuarSaque(ContaEspecial conta, double valor) {
		if(conta.saque(valor)) {
			System.out.println("O saque foi concluido. Seu saldo atual corresponde a: " + conta.getSaldo());
		}else {
			System.out.println("Voce nao possui saldo para este saque. Seu saldo atual corresponde a: " + conta.getSaldo());
		}
	}
}
