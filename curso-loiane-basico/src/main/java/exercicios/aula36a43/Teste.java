package exercicios.aula36a43;

public class Teste {
	
	public static void main(String[] args) {
	   ContaBancaria conta1 = new ContaBancaria("Cliente1", 123, 1000);
	   ContaPoupanca conta2 = new ContaPoupanca("Cliente2", 456, 2000, 15);
	   ContaEspecial conta3 = new ContaEspecial("Cliente3", 789, 1500, 500);
	
	   conta1.sacar(200);
	   conta2.depositar(300);
	   conta3.sacar(200);
	
	   conta2.calcularNovoSaldo(1.5);
	
	   conta1.mostrarDados();
	   conta2.mostrarDados();
	   conta3.mostrarDados();
	}
	
}