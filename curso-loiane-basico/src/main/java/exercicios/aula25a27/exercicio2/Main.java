package exercicios.aula25a27.exercicio2;

public class Main {
	
	public static void main(String[] args) {
	
		ContaCorrente contaCorrente = new ContaCorrente();
		
		contaCorrente.setSaldo(500);
		
		contaCorrente.setEspecial(true);
		
		contaCorrente.setLimiteEspecial(200);
		
		contaCorrente.consultarSaldo();
		
		contaCorrente.realizarSaque(500);
		
		contaCorrente.depositarDinheiro(500);
		
		contaCorrente.realizarSaque(600);
		
		System.out.println(	contaCorrente.chequeEspecial());

	}

}
