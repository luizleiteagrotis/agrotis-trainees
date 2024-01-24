package exercicios.aula28a33.exercicio2;

public class Main {

	public static void main(String[] args) {

		ContaCorrente contaCorrente = new ContaCorrente("1234", 1000.40, true, 220.70);
		
		contaCorrente.consultarSaldo();
		
		contaCorrente.realizarSaque(500);
		
		contaCorrente.depositarDinheiro(500);
		
		contaCorrente.realizarSaque(600);
		
		System.out.println(	contaCorrente.chequeEspecial());
	}

}
