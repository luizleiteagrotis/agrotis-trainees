package exercicios.aula36a43;

//Classe ContaBancaria
public class ContaBancaria {
 protected String nomeCliente;
 protected int numConta;
 protected double saldo;

 public ContaBancaria(String nomeCliente, int numConta, double saldo) {
     this.nomeCliente = nomeCliente;
     this.numConta = numConta;
     this.saldo = saldo;
 }

 public void sacar(double valor) {
     if (valor > 0 && saldo - valor >= 0) {
         saldo -= valor;
         System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
     } else {
         System.out.println("Saldo insuficiente para realizar o saque.");
     }
 }

 public void depositar(double valor) {
     if (valor > 0) {
         saldo += valor;
         System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
     } else {
         System.out.println("Valor de depósito inválido.");
     }
 }

 public void mostrarDados() {
     System.out.println("Nome do Cliente: " + nomeCliente);
     System.out.println("Número da Conta: " + numConta);
     System.out.println("Saldo: R$ " + saldo);
 }
}