package exercicios.aula37a43.exercicio1;

public class ContaEspecial extends ContaBancaria{

	private double limite;
	
	public ContaEspecial(String nomeCliente) {
		super(nomeCliente);
		limite = 200;
	}
	
	@Override
	public void sacar(double valor) throws ContaBancariaException {
		if (valor > saldo || saldo == 0) {
			double diferenca = valor - saldo;
			if (diferenca > limite) {
				throw new ContaEspecialException("Valor ultrapassou o limite");
			}
			saldo = 0;
			limite -= diferenca;
		} else {
			saldo -= valor;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tLimite: " + limite;
	}
}
