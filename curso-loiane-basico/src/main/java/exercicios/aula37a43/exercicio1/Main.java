package exercicios.aula37a43.exercicio1;

public class Main {

	public static void main(String[] args) {

		ContaBancaria contaSimples = new ContaBancaria();

		contaSimples.setNomeClinete("Jose");
		contaSimples.setNumConta("1234");
		contaSimples.setSaldo(2155.40);
		
		contaSimples.depositar(500);
		
		contaSimples.sacar(3000);

		
		
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		
		contaPoupanca.setNomeClinete("Jose");
		contaPoupanca.setNumConta("1234");
		contaPoupanca.setSaldo(2155.40);
		
		contaPoupanca.depositar(500);
		
		contaPoupanca.sacar(3000);
		
		contaPoupanca.setDiaRendimento(7);
		contaPoupanca.calcularNovoSaldo(0.5);
		System.out.println(contaPoupanca);
		

		
		
		ContaEspecial contaEspecial = new ContaEspecial();

		contaEspecial.setNomeClinete("Jose");
		contaEspecial.setNumConta("1234");
		contaEspecial.setSaldo(2155.40);
		
		contaEspecial.depositar(500);
		
		contaEspecial.setLimite(500);
		
		contaEspecial.sacar(3000);
		contaEspecial.sacar(200);

		System.out.println(contaEspecial);
	}
}
