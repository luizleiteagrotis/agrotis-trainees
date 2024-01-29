package exercicios.aula36a43;

public class ContaPoupanca extends ContaBancaria {
	 private int diaRendimento;

	 public ContaPoupanca(String nomeCliente, int numConta, double saldo, int diaRendimento) {
	     super(nomeCliente, numConta, saldo);
	     this.diaRendimento = diaRendimento;
	 }

	 public void calcularNovoSaldo(double taxaRendimento) {
	     if (diaRendimento == 0 /* Lógica para verificar se é o dia de rendimento */) {
	         saldo += saldo * (taxaRendimento / 100);
	         System.out.println("Rendimento aplicado. Novo saldo: R$ " + saldo);
	     } else {
	         System.out.println("Ainda não é o dia de rendimento.");
	     }
	 }
}
