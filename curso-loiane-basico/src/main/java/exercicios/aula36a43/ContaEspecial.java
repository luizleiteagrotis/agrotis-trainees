package exercicios.aula36a43;

public class ContaEspecial extends ContaBancaria {
	
	private double limite;

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public String toString() {
		return "ContaEspecial [limite = R$ " +  String.format("%.2f", limite) + ", nomeCliente = " + nomeCliente + ", numConta = " + numConta
				+ ", saldo = R$ " + String.format("%.2f", saldo) + "]";
	}
	
	public boolean sacar(double valorSaque) {
		if(saldo + limite > valorSaque) {
			saldo -= valorSaque;
			return true;
		}
		
		return false;
	}
		

	
}
