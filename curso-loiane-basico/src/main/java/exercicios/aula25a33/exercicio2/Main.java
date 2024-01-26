package exercicios.aula25a33.exercicio2;

public class Main {
	
	public static void main(String[] args) {
		long numeroConta = 1;
		double saldoConta = 0d;
		double limiteConta = 200;
		ContaCorrente contaCorrente = new ContaCorrente(numeroConta, saldoConta, limiteConta);
		
		System.out.println("Depositando valor");
		System.out.println(contaCorrente);
		contaCorrente.depositar(100);
		System.out.println(contaCorrente);
		
		System.out.println();
		
		System.out.println("Retirando valor indo para cheque especial");
		System.out.println(contaCorrente);
		contaCorrente.sacar(200);
		System.out.println(contaCorrente);
		
		System.out.println();
		
		System.out.println("Tentando retirar valor além do cheque especial");
		System.out.println(contaCorrente);
		contaCorrente.sacar(300);
		System.out.println(contaCorrente);
	}
}
