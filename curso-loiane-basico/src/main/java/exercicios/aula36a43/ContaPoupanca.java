package exercicios.aula36a43;

public class ContaPoupanca extends ContaBancaria{
	
	private int rendimento;
	
	public int getRendimento() {
		return rendimento;
	}
	
	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}
	
	@Override
	public String toString() {
        String str = " Dados da Conta Poupanca: ";
		
		str += " Cliente Conta Poupanca: \n" + rendimento;
		str += super.toString();;
		str += super.toString();;
		
		return str;
	}
	
	  public boolean calculoNovoSaldo (double taxa) {
		
		int diaAtual = 25;
		
		if (diaAtual == rendimento) {
			double rendimento = getSaldo() * taxa;
			depositar(rendimento);
			
			

		}
		return false;
		
	}
	  
	
	  
	
}
