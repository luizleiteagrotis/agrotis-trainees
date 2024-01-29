package exercicios.aula36a43;

public class ContaEspecial extends ContaBancaria {
	 private double limite;

	 public ContaEspecial(String nomeCliente, int numConta, double saldo, double limite) {
	     super(nomeCliente, numConta, saldo);
	     this.limite = limite;
	 }

	 @Override
	 public void sacar(double valor) {
	     if (valor > 0 && (saldo + limite) - valor >= 0) {
	         saldo -= valor;
	         System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
	     } else {
	         System.out.println("Saldo insuficiente para realizar o saque.");
	     }
	 }
}