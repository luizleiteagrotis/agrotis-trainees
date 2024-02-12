package exercicios.aula36a43;


public class ContaBancariaTeste {

	public static void main(String[] args) {
		
		System.out.println("---- TESTE CONTA BANCARIA ----");
		
		ContaBancaria contaSimples = new ContaBancaria();
		contaSimples.setNomeCliente("Humberto");
		contaSimples.setNumConta(76549);
		contaSimples.setSaldo(1000);
		
		System.out.println(contaSimples.toString());
		
		contaSimples.sacar(500);
		System.out.println(contaSimples.toString());
		contaSimples.depositar(200);
		System.out.println(contaSimples.toString());
		contaSimples.sacar(800);
		System.out.println(contaSimples.toString());
		
		System.out.println("---- TESTE CONTA POUPANÇA ----");
		
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		contaPoupanca.setNomeCliente("Edvalda");
		contaPoupanca.setNumConta(12987);
		contaPoupanca.setSaldo(1000);
		contaPoupanca.setDiaRendimento(12);
		
		System.out.println(contaPoupanca.toString());
		
		contaPoupanca.sacar(500);
		System.out.println(contaPoupanca.toString());
		contaPoupanca.depositar(200);
		System.out.println(contaPoupanca.toString());
		contaPoupanca.sacar(800);
		System.out.println(contaPoupanca.toString());
		contaPoupanca.calcularNovoSaldo(0.005);
		System.out.println(contaPoupanca.toString());
		
System.out.println("---- TESTE CONTA ESPECIAL ----");
		
		ContaEspecial contaEspecial = new ContaEspecial();
		contaEspecial.setNomeCliente("Tarciso");
		contaEspecial.setNumConta(58746);
		contaEspecial.setSaldo(1500);
		contaEspecial.setLimite(2000);
		
		
		System.out.println(contaEspecial.toString());
		contaEspecial.sacar(1500);
		System.out.println(contaEspecial.toString());
		contaEspecial.depositar(200);
		System.out.println(contaEspecial.toString());
		contaEspecial.sacar(800);
		System.out.println(contaEspecial.toString());
	}

}
