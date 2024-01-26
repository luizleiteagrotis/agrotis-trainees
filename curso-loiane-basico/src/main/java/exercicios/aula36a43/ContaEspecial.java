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
		 String str = " Dados da Conta Especial: \n";
			
			str += "Limite: \n" + limite;
			str += super.toString();;
			str += super.toString();;
			
			return str;
	}
	
	public boolean saque(double valor) {
		double limiteSaldo = this.getSaldo() + limite;
		
		if ((limiteSaldo-valor >=0)) {
			this.setSaldo(this.getSaldo()-valor);
			return true;
		}
		
		return false;
		
	}

}
